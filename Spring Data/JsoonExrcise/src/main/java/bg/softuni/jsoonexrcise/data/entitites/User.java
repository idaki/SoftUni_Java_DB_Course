package bg.softuni.jsoonexrcise.data.entitites;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String firstName;

    private String lastName;

    private Integer age;

    @OneToMany(mappedBy = "seller")
    private Set<Product> soldProducts = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<Product> boughtProducts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

    // Constructors, getters, and setters
}

