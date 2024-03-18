package bg.softuni.jsonexrcise.data.entitites;

import bg.softuni.jsonexrcise.data.entitites.BaseEntity.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category extends BaseEntity {

@Column
    private String name;

    @ManyToMany(mappedBy = "categories",targetEntity = Product.class)
    private Set<Product> products = new HashSet<>();

    public String getName() {
        return name;
    }

    public Set<Product> getProducts() {
        return products;
    }
}