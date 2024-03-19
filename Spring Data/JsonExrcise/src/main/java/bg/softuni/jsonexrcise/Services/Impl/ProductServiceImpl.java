package bg.softuni.jsonexrcise.Services.Impl;

import bg.softuni.jsonexrcise.Repository.ProductRepository;
import bg.softuni.jsonexrcise.Services.Dtos.ProductSeedDto;
import bg.softuni.jsonexrcise.Services.ProductService;
import bg.softuni.jsonexrcise.Services.UserService;
import bg.softuni.jsonexrcise.data.entitites.Product;
import bg.softuni.jsonexrcise.data.entitites.User;
import bg.softuni.jsonexrcise.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductServiceImpl implements ProductService {
    private final String FILE_PATH = "C:\\Users\\idaki.VENLO\\Documents\\GitHub\\SoftUni_Java_DB_Course\\Spring Data\\JsonExrcise\\src\\main\\resources\\json\\products.json";

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ValidationUtil validator;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ValidationUtil validator, Gson gson, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.validator = validator;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws IOException {

        if (productRepository.count() == 0) {

            String jsonContnet = new String(Files.readAllBytes(Path.of(FILE_PATH)));
            ProductSeedDto[] productSeedDto = gson.fromJson(jsonContnet, ProductSeedDto[].class);

            int count = 0;
            for (ProductSeedDto current : productSeedDto) {
                setBuyer(current, count);
                setSeller(current);
                if (!validator.isValid(current)) {
                    validator.getViolations(current)
                            .forEach(error -> System.out.println(error.getMessage()));
                    continue;
                }

                ;


                Product product = modelMapper.map(current, Product.class);
                this.productRepository.saveAndFlush(product);
            }


        }

    }

    private void setSeller(ProductSeedDto current) {
        int getRandomUserIndex = ThreadLocalRandom.current().nextInt(0, this.userService.getUserCount() + 1);
        User seller = this.userService.getUserById(getRandomUserIndex);
        current.setSeller(seller);
    }

    private void setBuyer(ProductSeedDto current, int count) {
        int getRandomUserIndex = ThreadLocalRandom.current().nextInt(0, this.userService.getUserCount());
        count++;
        if (count / 4   ==0) {
            User buyer = this.userService.getUserById(getRandomUserIndex);
            current.setBuyer(buyer);
        } else {
            current.setBuyer(null);
        }
    }

}



