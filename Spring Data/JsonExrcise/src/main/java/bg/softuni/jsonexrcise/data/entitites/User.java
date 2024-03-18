package bg.softuni.jsonexrcise.data.entitites;

import bg.softuni.jsonexrcise.data.entitites.BaseEntity.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends BaseEntity {
    @Column (name= "first_name",nullable = false)
    private String firstName;
    @Column (name= "last_name",nullable = false)
    private String lastName;
    @Column (nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "seller")
    private Set<Product> soldProducts = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<Product> boughtProducts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id",referencedColumnName = "id")
    )
    private Set<User> friends = new HashSet<>();

    // Constructors, getters, and setters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Product> getSoldProducts() {
        return soldProducts;
    }

    public Set<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public Set<User> getFriends() {
        return friends;
    }
}

