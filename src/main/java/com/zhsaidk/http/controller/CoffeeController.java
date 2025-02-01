package com.zhsaidk.http.controller;

import com.zhsaidk.database.dto.RecipeCreateDto;
import com.zhsaidk.service.CoffeeService;
import com.zhsaidk.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String doCoffee(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes){
        coffeeService.doCoffee(id);
        model.addAttribute("pageId", id);
        redirectAttributes.addAttribute("pageId", id);
        return "coffee/success";
    }

    @GetMapping("/create")
    public String createRecipePage(){
        return "coffee/create";
    }

    @PostMapping("/create/do")
    public String createRecipe(RecipeCreateDto recipeCreateDto){
        coffeeService.create(recipeCreateDto);
        return "redirect:/coffees";
    }

    @PostMapping("/{id}/delete")
    public String deleteCoffee(@PathVariable("id") Integer id){
        if (!coffeeService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/coffees";
    }
}
