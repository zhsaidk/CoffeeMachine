package com.zhsaidk.controller.http;

import com.zhsaidk.database.dto.IngredientTotalEditDto;
import com.zhsaidk.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredient/restock";
    }

    @PostMapping("/restock")
    public String restock(IngredientTotalEditDto ingredientCreateEditDto){
        ingredientService.restock(ingredientCreateEditDto);
        return "redirect:/ingredients";
    }
}
