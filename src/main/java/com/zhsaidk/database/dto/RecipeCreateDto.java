package com.zhsaidk.database.dto;

import lombok.Value;

@Value
public class RecipeCreateDto {
    String name;
    Integer water_lvl;
    Integer milk_lvl;
    Integer sugar_lvl;
    Integer chocolate_lvl;
}
