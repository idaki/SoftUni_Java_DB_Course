package bg.softuni.springdataautomappingobjectslab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;

    public City() {
    }

    public Long getId() {
        return id;
    }
@Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}