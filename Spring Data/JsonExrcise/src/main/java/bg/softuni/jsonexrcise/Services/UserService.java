package bg.softuni.jsonexrcise.Services;

import bg.softuni.jsonexrcise.data.entitites.User;

import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException;

    User getUserById(int id);
    int getUserCount();

}
