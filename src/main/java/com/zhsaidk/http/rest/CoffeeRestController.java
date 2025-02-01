package com.zhsaidk.http.rest;

import com.zhsaidk.database.dto.CoffeeReadDto;
import com.zhsaidk.database.dto.RecipeCreateDto;
import com.zhsaidk.database.repository.CoffeeRepository;
import com.zhsaidk.mapper.CoffeeReadMapper;
import com.zhsaidk.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
public class CoffeeRestController {
    private final CoffeeService coffeeService;
    private final CoffeeReadMapper coffeeReadMapper;

    @GetMapping
    public ResponseEntity<List<CoffeeReadDto>> getAll(){
        return ResponseEntity.ok(coffeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeReadDto> findById(@PathVariable("id") Integer id,
                                                  Model model){
        return Optional.ofNullable(coffeeService.findById(id))
                .map(coffeeReadMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create/do")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeCreateDto recipeCreateDto){
        coffeeService.create(recipeCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteCoffee(@PathVariable("id") Integer id){
        return coffeeService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
