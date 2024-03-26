package bg.softuni.jsonexrcise.Services.Dtos;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsersSeedDto {

    @Expose
    @Digits(integer = 11, fraction = 0)
    private int age;
    @Expose
    private String firstName;

    @Expose
    @NotNull
    private String lastName;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
