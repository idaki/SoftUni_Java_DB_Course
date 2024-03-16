package com.example.gamestore_automapping.repositories;

import com.example.gamestore_automapping.data.entities.User;
import com.example.gamestore_automapping.services.dtos.UserLoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
