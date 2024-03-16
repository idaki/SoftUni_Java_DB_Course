package com.example.gamestore_automapping.services.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {

    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain Capital letter, small letter and number")
    @Size(min = 6, message = "Password must consist of at least 6 symbols")
    private String password;
    private String confirmedPassword;
    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmedPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.fullName = fullName;
    }

    public  String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
