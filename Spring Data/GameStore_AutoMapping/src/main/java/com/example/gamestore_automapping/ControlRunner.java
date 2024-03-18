package com.example.gamestore_automapping;

import com.example.gamestore_automapping.services.GameService;
import com.example.gamestore_automapping.services.dtos.GameAddDTO;
import com.example.gamestore_automapping.services.dtos.UserLoginDTO;
import com.example.gamestore_automapping.services.dtos.UserRegisterDTO;
import com.example.gamestore_automapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ControlRunner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    public ControlRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        while (!input.equals("END")) {

            String tokens[] = input.split("\\|+");

            String command = "";
            if (tokens[0].equals("RegisterUser")) {
                command = this.userService.registerUser(new UserRegisterDTO(tokens[1], tokens[2], tokens[3], tokens[4]));
            } else if (tokens[0].equals("LoginUser")) {
              command=  this.userService.loginUser(new UserLoginDTO(tokens[1], tokens[2]));
            } else if (tokens[0].equals("Logout")) {
             command= this.userService.logoutUser();
            } else if (tokens[0].equals("AddGame")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

              command = this.gameService.addGame(new GameAddDTO(
                      tokens[1],
                      Double.parseDouble(tokens[2]),
                      Double.parseDouble(tokens[3]),
                      tokens[4],
                      tokens[5],
                      tokens[6],
                      LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("d-MM-yyyy"))
                      ));

            }else if (tokens[0].equals("EditGame")) {

            }

            System.out.println(command);

            input = reader.readLine();
        }

    }
}
