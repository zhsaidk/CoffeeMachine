package com.zhsaidk.http.rest;

import com.zhsaidk.database.dto.CoffeeReadDto;
import com.zhsaidk.database.repository.CoffeeRepository;
import com.zhsaidk.mapper.CoffeeReadMapper;
import com.zhsaidk.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
public class CoffeeRestController {
    private final CoffeeService coffeeService;
    @GetMapping("/coffees")
    public List<CoffeeReadDto> getAll(){
        return coffeeService.findAll();
    }
}
