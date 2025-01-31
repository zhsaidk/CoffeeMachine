package com.zhsaidk.database.dto;

import com.zhsaidk.database.entity.Ingredient;
import lombok.Value;

@Value
public class RecipeReadDto {
    Integer id;
    Integer mass;
    IngredientReadDto ingredientReadDto;
}
