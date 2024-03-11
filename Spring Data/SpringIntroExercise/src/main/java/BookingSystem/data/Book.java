package BookingSystem.data;

import BookingSystem.data.Base.BaseEntity;
import BookingSystem.data.Enum.AgeRestriction;
import BookingSystem.data.Enum.BookEditionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")

public class Book extends BaseEntity {


    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookEditionType editionType;
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private double copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;





    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Book() {
    }

    public Book(String title, BookEditionType editionType, BigDecimal price, LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories, int copies) {
    this.title = title;
    this.editionType =editionType;
    this.price = price;
    this.releaseDate = releaseDate;
    this.ageRestriction= ageRestriction;
    this.author = author;
    this.categories = categories;
    this.copies = copies;

    }
}
