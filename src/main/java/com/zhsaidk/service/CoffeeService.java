package com.zhsaidk.service;

import com.zhsaidk.database.dto.CoffeeReadDto;
import com.zhsaidk.database.entity.Coffee;
import com.zhsaidk.database.entity.Ingredient;
import com.zhsaidk.database.entity.Recipe;
import com.zhsaidk.database.repository.CoffeeRepository;
import com.zhsaidk.database.repository.IngredientRepository;
import com.zhsaidk.database.repository.RecipeRepository;
import com.zhsaidk.mapper.CoffeeReadMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final CoffeeReadMapper coffeeReadMapper;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public List<CoffeeReadDto> findAll(){
        return coffeeRepository.findAll(Sort.by("count").descending())
                .stream()
                .map(coffeeReadMapper::map)
                .toList();
    }

    @Transactional
    public Coffee findById(Integer id){
        return coffeeRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void doCoffee(Integer id) {
        List<Recipe> recipes = recipeRepository.findByCoffeeId(id);

        for (Recipe recipe : recipes) {
            Ingredient ingredient = recipe.getIngredient();

            if (ingredient.getTotal() < recipe.getMass()) {
                throw new IllegalStateException("Не хватит ингредиенты");
            }

            ingredient.setTotal(ingredient.getTotal() - recipe.getMass());
            ingredientRepository.save(ingredient);
        }

        Coffee coffee = coffeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        coffee.setCount(coffee.getCount() + 1);
        coffeeRepository.save(coffee);
    }
}
