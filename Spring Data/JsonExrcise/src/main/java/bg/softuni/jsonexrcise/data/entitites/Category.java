package bg.softuni.jsonexrcise.data.entitites;

import bg.softuni.jsonexrcise.data.entitites.BaseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name="categories")
public class Category extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String name;

    @Column
    @ManyToMany (mappedBy = "categories", targetEntity = Product.class)
    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
