package com.example.advquerying.Repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
