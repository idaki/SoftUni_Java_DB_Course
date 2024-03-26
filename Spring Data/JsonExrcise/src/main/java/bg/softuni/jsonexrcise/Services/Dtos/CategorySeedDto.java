package bg.softuni.jsonexrcise.Services.Dtos;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategorySeedDto {

    @Expose
    @Size(min=3, max = 15)
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
