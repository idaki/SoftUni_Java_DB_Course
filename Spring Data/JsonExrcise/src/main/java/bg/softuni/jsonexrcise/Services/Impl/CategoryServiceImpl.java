package bg.softuni.jsonexrcise.Services.Impl;

import bg.softuni.jsonexrcise.Repository.CategoryRepository;
import bg.softuni.jsonexrcise.Services.CategoryService;
import bg.softuni.jsonexrcise.Services.Dtos.CategorySeedDto;
import bg.softuni.jsonexrcise.data.entitites.Category;
import bg.softuni.jsonexrcise.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final String FILE_PATH ="C:\\Users\\idaki.VENLO\\Documents\\GitHub\\SoftUni_Java_DB_Course\\Spring Data\\JsonExrcise\\src\\main\\resources\\json\\categories.json" ;

    private final CategoryRepository categoryRepository;
    private final ValidationUtil validator;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validator, Gson gson, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            String jsonContent = new String(Files.readAllBytes(Path.of(FILE_PATH)));
            CategorySeedDto[] categoriesSeedDto = this.gson.fromJson(jsonContent, CategorySeedDto[].class);

            for (CategorySeedDto current : categoriesSeedDto   ) {
                if (!this.validator.isValid(current)) {
                    this.validator.getViolations(current)
                            .forEach(error -> System.out.println(error.getMessage()));
                    continue;
                }
                Category category = modelMapper.map(current, Category.class);
                categoryRepository.saveAndFlush(category);

            }
       }

    }
}