package com.example.gamestore_automapping.services;

import com.example.gamestore_automapping.data.entities.User;
import com.example.gamestore_automapping.services.dtos.UserLoginDTO;
import com.example.gamestore_automapping.services.dtos.UserRegisterDTO;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {
    String registerUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    String logoutUser();
}
