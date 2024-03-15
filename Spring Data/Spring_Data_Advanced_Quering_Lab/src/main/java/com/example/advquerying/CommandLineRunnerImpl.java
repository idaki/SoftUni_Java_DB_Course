package com.example.advquerying;

import com.example.advquerying.Services.Impl.IngredientServiceImpl;
import com.example.advquerying.Services.Impl.LabelServiceImpl;
import com.example.advquerying.Services.Impl.ShampooServiceImpl;
import com.example.advquerying.entities.Shampoo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ShampooServiceImpl shampooService;
    private final IngredientServiceImpl ingredientService;

    private final LabelServiceImpl labelService;

    public CommandLineRunnerImpl(ShampooServiceImpl shampooService, IngredientServiceImpl ingredientService, LabelServiceImpl labelService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.labelService = labelService;
    }

    @Override
    public void run(String... args) throws Exception {
        // shampooService.findAllShampoosBySize("MEDIUM");
      //  shampooService.findAllShampoosBySizeOrLabelId("medium", 10);
      //  shampooService.findAllShampoosByPrice(BigDecimal.valueOf(5));
    }
}
