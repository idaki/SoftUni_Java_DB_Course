package BookingSystem.Services;

import BookingSystem.data.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;


    List<String> getTitlesOfAllBooksReleasedAfter(LocalDate date);

    List<String> getBooksTitleReleaseDateAndCopiesByAuthorNames(String firstName, String lastName);

}
