package bg.softuni.jsonexrcise;

import bg.softuni.jsonexrcise.Services.CategoryService;
import bg.softuni.jsonexrcise.Services.ProductService;
import bg.softuni.jsonexrcise.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ControlRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;

    public ControlRunner(CategoryService categoryService, ProductService productService, UserService userService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
           productService.seedProducts();
       categoryService.seedCategories();



    }
}
