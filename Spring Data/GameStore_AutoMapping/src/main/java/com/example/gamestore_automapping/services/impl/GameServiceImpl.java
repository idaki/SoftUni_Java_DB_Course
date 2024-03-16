package com.example.gamestore_automapping.services.impl;

import com.example.gamestore_automapping.data.entities.Game;
import com.example.gamestore_automapping.repositories.GameRepository;
import com.example.gamestore_automapping.services.GameService;
import com.example.gamestore_automapping.services.dtos.GameAddDTO;
import com.example.gamestore_automapping.utils.ValidatorService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidatorService validatorService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidatorService validatorService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validatorService = validatorService;
    }

    @Override
    public String addGame(GameAddDTO gameAddDTO) {

        if (!validatorService.isValid(gameAddDTO)) {
            return validatorService.validate(gameAddDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        Game game = this.modelMapper.map(gameAddDTO, Game.class);
        this.gameRepository.saveAndFlush(game);
        return String.format("Added %s",game.getTitle());
    }
}
