package com.example.gamestore_automapping.services.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GameAddDTO {
    @Size(min = 3, max = 100, message = "Length should be between 3 and 100 symbols")
    @Pattern(regexp = "[A-Z]\\w+")
    private String title;
    @Min(value = 0)
    private double price;
    @Min(value = 0)
    private double size;
 //   @Pattern(regexp = "^(https?://\\w+)")
    private String trailer;
    private String imageThumbnail;
    @Size (min=20)
    private String description;
    private LocalDate releaseDate;

    public GameAddDTO(String title, double price, double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }


    public double getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}

