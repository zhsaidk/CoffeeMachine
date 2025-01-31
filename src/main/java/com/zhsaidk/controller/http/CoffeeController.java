package com.zhsaidk.controller.http;

import com.zhsaidk.database.repository.CoffeeRepository;
import com.zhsaidk.service.CoffeeService;
import com.zhsaidk.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffees")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final RecipeService recipeService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("allTypes", coffeeService.findAll());
        return "coffee/catalog";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model){
        model.addAttribute("coffee", coffeeService.findById(id));
        model.addAttribute("recipe", recipeService.findById(id));
        return "coffee/coffee";
    }

    @PostMapping("/{id}/do")
    public String doCoffee(@PathVariable("id") Integer id){
        coffeeService.doCoffee(id);
        return "redirect:/coffees/" + id;
    }
}
