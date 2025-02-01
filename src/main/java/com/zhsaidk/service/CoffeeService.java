package com.zhsaidk.service;

import com.zhsaidk.database.dto.CoffeeReadDto;
import com.zhsaidk.database.dto.RecipeCreateDto;
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

    public List<CoffeeReadDto> findAll() {
        return coffeeRepository.findAll(Sort.by("count").descending())
                .stream()
                .map(coffeeReadMapper::map)
                .toList();
    }

    @Transactional
    public Coffee findById(Integer id) {
        return coffeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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

    @Transactional
    public void create(RecipeCreateDto createDto) {
        if (createDto.getName() == null || createDto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            Coffee coffee = new Coffee();
            coffee.setName(createDto.getName());
            coffee.setCount(0); //Инициализация
            Coffee createdCoffee = coffeeRepository.saveAndFlush(coffee);

            createNewRecipe("Water", createDto.getWater_lvl(), createdCoffee);
            createNewRecipe("Milk", createDto.getMilk_lvl(), createdCoffee);
            createNewRecipe("Sugar", createDto.getSugar_lvl(), createdCoffee);
            createNewRecipe("Chocolate", createDto.getChocolate_lvl(), createdCoffee);
        }
    }

    public void createNewRecipe(String title, Integer lvl, Coffee coffee) {
        if (lvl != null && lvl != 0) {

            Ingredient ingredient = ingredientRepository.findByTitle(title)
                    .orElseThrow(() -> new RuntimeException("ingredient not found"));

            Recipe recipe = new Recipe();
            recipe.setCoffee(coffee);
            recipe.setMass(lvl);
            recipe.setIngredient(ingredient);
            recipeRepository.save(recipe);
        }
    }

    @Transactional
    public Boolean delete(Integer id) {
        if (!coffeeRepository.existsById(id)){
            return false;
        }
        coffeeRepository.deleteById(id);
        return true;
    }
}
