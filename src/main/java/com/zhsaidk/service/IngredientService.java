package com.zhsaidk.service;

import com.zhsaidk.database.dto.IngredientTotalEditDto;
import com.zhsaidk.database.dto.IngredientReadDto;
import com.zhsaidk.database.entity.Ingredient;
import com.zhsaidk.database.repository.IngredientRepository;
import com.zhsaidk.mapper.IngredientReadMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientReadMapper ingredientReadMapper;

    public List<IngredientReadDto> findAll(){
        return ingredientRepository.findAll(Sort.by("id")).stream()
                .map(ingredientReadMapper::map)
                .toList();
    }


    @Transactional
    public void restock(IngredientTotalEditDto editDto) {
        updateTotal("Water", editDto.getWater_lvl());
        updateTotal("Milk", editDto.getMilk_lvl());
        updateTotal("Sugar", editDto.getSugar_lvl());
        updateTotal("Chocolate", editDto.getChocolate_lvl());
    }

    public void updateTotal(String title, Integer lvl) {
        Ingredient ingredient = ingredientRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ingredient.setTotal(lvl);
        ingredientRepository.save(ingredient);
    }
}
