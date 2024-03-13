package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
     //  seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
       // pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
    //  printAllBooksByAgeRestrictions();
      //  printAllBooksByEditionTypeAndCopiesLessThan();

        printAllBooksInPriceRange();
    }

    private void printAllBooksInPriceRange() {
        this.bookService.findAllBooksWithLowerPriceAndHigher (BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .forEach(System.out::println);
    }

    private void printAllBooksByEditionTypeAndCopiesLessThan() {


        bookService.findAllBooksByEditionTypeAndCopies(EditionType.valueOf("GOLD"),5000)
                .forEach(System.out::println);
    }

    private void printAllBooksByAgeRestrictions() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AgeRestriction ageRestrictionEnum = AgeRestriction.valueOf(reader.readLine().toUpperCase());
   bookService.findAllBookTitlesByAgeRestriction(ageRestrictionEnum)
           .forEach(System.out::println);


    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
