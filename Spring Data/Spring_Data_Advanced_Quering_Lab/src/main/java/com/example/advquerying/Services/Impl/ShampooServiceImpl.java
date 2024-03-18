package com.example.advquerying.Services.Impl;

import com.example.advquerying.Repositories.ShampooRepository;
import com.example.advquerying.Services.ShampooService;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> findAllShampoosBySize(String size) {
        Size enumSize = Size.valueOf(size.toUpperCase());
        List<Shampoo> result = this.shampooRepository.findAllShampoosBySize(enumSize);
        result.forEach(shampoo -> System.out.printf("%s %s%n",shampoo.getBrand(),shampoo.getSize().name()));
        return result;
    }

    @Override
    public List<Shampoo> findAllShampoosBySizeOrLabelId(String size, long labelId) {
        Size enumSize = Size.valueOf(size.toUpperCase());
        List<Shampoo> result = this.shampooRepository.findAllShampoosBySizeOrLabelId(enumSize,labelId);
        result.forEach(shampoo -> System.out.printf("%s %s%n",shampoo.getBrand(),shampoo.getSize().name()));
        return result;
    }

    @Override
    public List<Shampoo> findAllShampoosByPrice(BigDecimal price) {
       List<Shampoo> result = this.shampooRepository.findAllShampoosByPrice(price);
        result.forEach(shampoo -> System.out.printf("%s %.2f", shampoo.getBrand(),shampoo.getPrice()));
        return result;
    }


}
