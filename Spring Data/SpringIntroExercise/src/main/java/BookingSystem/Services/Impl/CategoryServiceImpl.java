package BookingSystem.Services.Impl;

import BookingSystem.Reporitories.CategoryRepository;
import BookingSystem.Services.CategoryService;
import BookingSystem.data.Author;
import BookingSystem.data.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String FILE_PATH = "src/main/resources/files/categories.txt";
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(row -> {
                    Category category = new Category(row);
                    this.categoryRepository.saveAndFlush(category);
                });
//
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);
        for (int i = 0; i < randomCount; i++) {
          int randomId = ThreadLocalRandom.current().nextInt(1, (int)this.categoryRepository.count()+1 );
            categories.add(this.categoryRepository.findById(randomId).get());
        }
        return categories;
    }
}
