package enteties;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.time.LocalDate;
@Entity(name="users")
public class User {
    @Id
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name ="age")
    private int age;
    @Column(name="registration_date")
    private LocalDate registration_date;


    public User(String username, String password, int age,LocalDate registration_date ) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.registration_date = registration_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }
}
