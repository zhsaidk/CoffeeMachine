package com.zhsaidk.database.repository;

import com.zhsaidk.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    //todo 31.01.2025 есть чиклическая зависимость, временно заменил на nativeQuery
    @Query(value = "select * from Recipe where coffee_id=:coffeeId", nativeQuery = true)
    List<Recipe> findByCoffeeId(Integer coffeeId);
}
