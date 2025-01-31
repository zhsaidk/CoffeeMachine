package com.zhsaidk.service;

import com.zhsaidk.database.dto.RecipeReadDto;
import com.zhsaidk.database.entity.Ingredient;
import com.zhsaidk.database.entity.Recipe;
import com.zhsaidk.database.repository.IngredientRepository;
import com.zhsaidk.database.repository.RecipeRepository;
import com.zhsaidk.mapper.IngredientReadMapper;
import com.zhsaidk.mapper.RecipeReadMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeReadMapper recipeReadMapper;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public List<RecipeReadDto> findById(Integer id) {
        return recipeRepository.findByCoffeeId(id)
                .stream().map(recipeReadMapper::map)
                .toList();
    }

}
