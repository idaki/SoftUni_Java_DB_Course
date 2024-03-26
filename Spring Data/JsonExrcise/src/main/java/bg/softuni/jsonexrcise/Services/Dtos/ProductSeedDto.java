package bg.softuni.jsonexrcise.Services.Dtos;

import bg.softuni.jsonexrcise.data.entitites.User;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ProductSeedDto {

    @Expose
    private String name;

    @Expose
    private double price;

    @Expose
    private User buyer;

    @Expose
@NotNull
    private User seller;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
