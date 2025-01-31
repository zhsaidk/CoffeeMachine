package com.zhsaidk.mapper;

import com.zhsaidk.database.dto.IngredientReadDto;
import com.zhsaidk.database.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientReadMapper implements Mapper<Ingredient, IngredientReadDto>{
    @Override
    public IngredientReadDto map(Ingredient ingredient) {
        return new IngredientReadDto(
                ingredient.getTitle(),
                ingredient.getTotal()
        );
    }
}
