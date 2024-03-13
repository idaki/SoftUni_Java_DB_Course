package BookingSystem.Services.Impl;

import BookingSystem.Reporitories.BookRepository;
import BookingSystem.Services.AuthorService;
import BookingSystem.Services.BookService;
import BookingSystem.Services.CategoryService;
import BookingSystem.data.Author;
import BookingSystem.data.Book;
import BookingSystem.data.Category;
import BookingSystem.data.Enum.AgeRestriction;
import BookingSystem.data.Enum.BookEditionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final static String FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    private final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(FILE_PATH))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = this.authorService.getRandomAuthor();
                    BookEditionType editionType = BookEditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = this.categoryService.getRandomCategories();

                    Book book = new Book(title, editionType, price, releaseDate,
                            ageRestriction, author, categories, copies);

                    bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public List<String> getTitlesOfAllBooksReleasedAfter(final LocalDate date) {
        return this.bookRepository
                .getBooksByReleaseDateAfter(date)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(final String firstName, final String lastName) {
        return this.bookRepository
                .getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .stream()
                .map(book -> String.format("%s - %s - %d",
                        book.getTitle(), book.getReleaseDate().toString(), book.getCopies()))
                .collect(Collectors.toList());
    }


}
