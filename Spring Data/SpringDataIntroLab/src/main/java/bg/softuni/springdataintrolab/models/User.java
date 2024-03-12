package bg.softuni.springdataintrolab.models;

import bg.softuni.springdataintrolab.models.Account;
import bg.softuni.springdataintrolab.models.BaseEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Age")
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    // Constructors

    public User() {
        this.accounts = new HashSet<>();
    }



}
