package com.example.dania.controlerGUI;

import com.example.dania.entity.Dish;
import com.example.dania.entity.Ingredient;
import com.example.dania.service.DishService;
import com.example.dania.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminEditDishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/admin/dishedit/{dishId}")
    public String home(@PathVariable Long dishId, Model model){
        Dish dish = dishService.getDish(dishId);
        Set<Ingredient> ingredientsOfDish = dish.getIngredients();
        List<Ingredient> ingredientList = ingredientService.getAllIngredients();
        model.addAttribute("ingredients",ingredientList);
        model.addAttribute("dish", dish);
        model.addAttribute("ingredientsOfDish",ingredientsOfDish);
        return "editdish";
    }

    @GetMapping("/admin/dishedit/add/{dishId}/{ingredientId}")
    public String addIngredientToDish(@PathVariable Long dishId,@PathVariable Long ingredientId){
        dishService.addIngredientToDish(dishId,ingredientId);

        return "redirect:/admin/dishedit/" + dishId;
    }

    @GetMapping("/admin/dishedit/del/{dishId}/{ingredientId}")
    public String delIngredientToDish(@PathVariable Long dishId, @PathVariable Long ingredientId){
        dishService.removeIngredientFromDish(dishId,ingredientId);

        return "redirect:/admin/dishedit/" + dishId;
    }
}
