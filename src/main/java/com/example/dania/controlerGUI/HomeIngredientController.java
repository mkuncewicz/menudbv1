package com.example.dania.controlerGUI;

import com.example.dania.entity.Dish;
import com.example.dania.entity.Ingredient;
import com.example.dania.repository.DishRepository;
import com.example.dania.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeIngredientController {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> ingredientList = new ArrayList<>();


    @GetMapping("/testowa/{dishId}")
    public String home(@PathVariable Long dishId, Model model){
        Optional<Dish> optionalDish = dishRepository.findById(dishId);
        Dish dish = optionalDish.get();

        ingredientList = (List<Ingredient>) ingredientRepository.findAll();
        model.addAttribute("ingredients",ingredientList);
        model.addAttribute("dish", dish);
        return "druga";
    }

    @GetMapping("/testowa/add/{dishId}/{ingredientId}")
    public String addIngredientToDish(@PathVariable Long dishId,@PathVariable Long ingredientId){
        Optional<Dish> dish = dishRepository.findById(dishId);
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);

        if(dish.isPresent() && ingredient.isPresent()) {
            Dish saveDish = dish.get();
            Ingredient saveIngredient = ingredient.get();
            saveDish.getIngredients().add(saveIngredient);
            saveIngredient.getDishes().add(saveDish);
            dishRepository.save(saveDish);
            ingredientRepository.save(saveIngredient);
        }

        return "redirect:/";
    }

    @GetMapping("/testowa/del/{dishId}/{ingredientId}")
    public String delIngredientToDish(@PathVariable Long dishId,@PathVariable Long ingredientId){
        Optional<Dish> dish = dishRepository.findById(dishId);
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);

        if(dish.isPresent() && ingredient.isPresent()) {
            Dish saveDish = dish.get();
            Ingredient saveIngredient = ingredient.get();
            saveDish.getIngredients().remove(saveIngredient);
            dishRepository.save(saveDish);
            ingredientRepository.save(saveIngredient);
        }

        return "redirect:/";
    }
}
