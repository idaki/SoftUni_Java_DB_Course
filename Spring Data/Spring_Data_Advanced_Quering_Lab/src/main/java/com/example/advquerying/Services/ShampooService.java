package com.example.advquerying.Services;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> findAllShampoosBySize(String size);

    List<Shampoo> findAllShampoosBySizeOrLabelId(String size, long labelId);

    List<Shampoo> findAllShampoosByPrice(BigDecimal price);
}
