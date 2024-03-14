package bg.softuni.springdataautomappingobjectslab.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name= "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Basic
    private String city;

    public Address() {
    }

    public Long getId() {
        return id;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
