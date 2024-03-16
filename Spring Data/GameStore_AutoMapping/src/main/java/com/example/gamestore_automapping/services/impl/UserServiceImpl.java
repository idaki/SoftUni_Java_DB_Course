package com.example.gamestore_automapping.services.impl;

import com.example.gamestore_automapping.data.entities.User;
import com.example.gamestore_automapping.repositories.UserRepository;
import com.example.gamestore_automapping.services.UserService;
import com.example.gamestore_automapping.services.dtos.UserLoginDTO;
import com.example.gamestore_automapping.services.dtos.UserRegisterDTO;
import com.example.gamestore_automapping.utils.ValidatorService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private User loggedIn;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidatorService validatorService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(UserRegisterDTO userRegisterDTO) {
        if (!validatorService.isValid(userRegisterDTO)) {
            Set<ConstraintViolation<UserRegisterDTO>> violationSet = this.validatorService.validate(userRegisterDTO);
            return violationSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmedPassword())) {
            return "Password does not match";
        }

        Optional<User> userByEmail = this.userRepository.findUserByEmail(userRegisterDTO.getEmail());

        if (userByEmail.isPresent()) {
            return "User with this email already exists";
        }
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        this.userRepository.saveAndFlush(user);

        return String.format("%s was registered", user.getFullName());
    }

    @Override
    public String loginUser(UserLoginDTO userLoginDTO) {

        Optional<User> user = this.userRepository.findUserByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        if (user.isEmpty()) {
            return "Incorrect username / password";
        }

        this.loggedIn = user.get();

        return String.format("Successfully logged in  %s", loggedIn.getFullName());
    }

    @Override
    public String logoutUser() {
        if (loggedIn == null){
            return "Cannot log out. No user was logged in.";
        }

        String output = String.format("Successfully logged out %s",loggedIn.getFullName());
        loggedIn= null;
        return output;
    }


}
