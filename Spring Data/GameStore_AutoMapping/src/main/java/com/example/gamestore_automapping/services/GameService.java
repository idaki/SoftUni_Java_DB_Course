package com.example.gamestore_automapping.services;

import com.example.gamestore_automapping.services.dtos.GameAddDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GameService
{
    String addGame(GameAddDTO gameAddDTO);
}
