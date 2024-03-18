package com.example.advquerying.Repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public  interface ShampooRepository extends JpaRepository<Shampoo, Long> {

     List<Shampoo> findAllShampoosBySize(Size size);

     List<Shampoo> findAllShampoosBySizeOrLabelId(Size size, long labelId);

     List<Shampoo> findAllShampoosByPrice(BigDecimal price);
}
