package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllBooksByEditionTypeAndCopies(EditionType editionType, int copies);

    List<String> findAllBooksWithLowerPriceAndHigher(BigDecimal minPrice, BigDecimal maxPrice);


}
