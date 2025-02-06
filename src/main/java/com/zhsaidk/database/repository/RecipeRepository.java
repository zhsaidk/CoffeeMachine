package com.zhsaidk.database.repository;

import com.zhsaidk.database.entity.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    //todo 31.01.2025 есть чиклическая зависимость, временно заменил на nativeQuery
    @Query("SELECT r FROM Recipe r JOIN FETCH r.coffee JOIN FETCH r.ingredient WHERE r.coffee.id = :coffeeId")
    List<Recipe> findRecipeByCoffeeId(Integer coffeeId);

}
