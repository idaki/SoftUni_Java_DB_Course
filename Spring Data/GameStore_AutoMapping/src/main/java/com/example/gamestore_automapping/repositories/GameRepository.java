package com.example.gamestore_automapping.repositories;

import com.example.gamestore_automapping.data.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
}
