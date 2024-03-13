package BookingSystem.Reporitories;

import BookingSystem.data.Author;
import BookingSystem.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getBooksByReleaseDateAfter(LocalDate date);

    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc
            (String firstName, String lastName);
}


