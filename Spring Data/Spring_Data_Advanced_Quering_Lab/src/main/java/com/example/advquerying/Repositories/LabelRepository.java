package com.example.advquerying.Repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface LabelRepository extends JpaRepository<Label, Long> {
}
