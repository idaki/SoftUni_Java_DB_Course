package bg.softuni.jsonexrcise.Services.Impl;

import bg.softuni.jsonexrcise.Repository.UserRepository;
import bg.softuni.jsonexrcise.Services.Dtos.UsersSeedDto;
import bg.softuni.jsonexrcise.Services.UserService;
import bg.softuni.jsonexrcise.data.entitites.User;
import bg.softuni.jsonexrcise.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class UserServiceImpl implements UserService {

    private final String FILE_PATH = "C:\\Users\\idaki.VENLO\\Documents\\GitHub\\SoftUni_Java_DB_Course\\Spring Data\\JsonExrcise\\src\\main\\resources\\json\\users.json";

    private final UserRepository userRepository;
    private final ValidationUtil validator;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidationUtil validator, Gson gson, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Path.of(FILE_PATH)));
        UsersSeedDto[] usersSeedDtos = gson.fromJson(jsonContent, UsersSeedDto[].class);

        if (userRepository.count() == 0) {
            for (UsersSeedDto current : usersSeedDtos) {
                if (!validator.isValid(current)){
                    validator.getViolations(current)
                            .forEach(error -> System.out.println(error.getMessage()));
                    continue;
                }
                User user = modelMapper.map(current, User.class);
                this.userRepository.saveAndFlush(user);


            }
        }
    }

    @Override
    public User getUserById(int id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public int getUserCount() {
        return (int) this.userRepository.count();
    }
}
