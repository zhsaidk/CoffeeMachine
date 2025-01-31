package com.zhsaidk.mapper;

import com.zhsaidk.database.dto.CoffeeReadDto;
import com.zhsaidk.database.entity.Coffee;
import org.springframework.stereotype.Component;

@Component
public class CoffeeReadMapper implements Mapper<Coffee, CoffeeReadDto>{
    @Override
    public CoffeeReadDto map(Coffee coffee) {
        return new CoffeeReadDto(
                coffee.getId(),
                coffee.getName(),
                coffee.getCount()
        );
    }
}
