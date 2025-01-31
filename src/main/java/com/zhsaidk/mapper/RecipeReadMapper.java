package com.zhsaidk.mapper;

import com.zhsaidk.database.dto.IngredientReadDto;
import com.zhsaidk.database.dto.RecipeReadDto;
import com.zhsaidk.database.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeReadMapper implements Mapper<Recipe, RecipeReadDto> {
    private final IngredientReadMapper ingredientReadMapper;

    @Override
    public RecipeReadDto map(Recipe recipe) {
        return new RecipeReadDto(
                recipe.getId(),
                recipe.getMass(),
                ingredientReadMapper.map(recipe.getIngredient())
        );
    }
}
