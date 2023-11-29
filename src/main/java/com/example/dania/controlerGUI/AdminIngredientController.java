package com.example.dania.controlerGUI;

import com.example.dania.dto.IngredientDto;
import com.example.dania.entity.Ingredient;
import com.example.dania.mapper.IngredientMapper;
import com.example.dania.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminIngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @GetMapping("/admin/ingredient")
    public String home(Model model){
        List<Ingredient> ingredientList = ingredientService.getAllIngredients();

        model.addAttribute("ingredients",ingredientList);
        model.addAttribute("newIngredient",new IngredientDto());
        model.addAttribute("updateIngredient",new IngredientDto());
        return "adminingredient";
    }

    @PostMapping("/admin/ingredient/add-ingredient")
    public String createDish(@ModelAttribute("newIngredient")IngredientDto ingredientDto){
        Ingredient saveIngredient = ingredientMapper.mapToEntity(ingredientDto);
        ingredientService.createIngredient(saveIngredient);

        return "redirect:/admin/ingredient";
    }

    @PostMapping("/admin/ingredient/del-ingredient")
    public String delIngredient(@RequestParam(name = "id") Long ingredientId){
        ingredientService.deleteIngredient(ingredientId);

        return "redirect:/admin/ingredient";
    }

    @PostMapping("/admin/ingredient/update-ingredient")
    public String updateIngredient(@RequestParam(name = "id") Long ingredientId,@ModelAttribute("updateIngredient")IngredientDto ingredientDto){
        Ingredient saveIngredient = ingredientMapper.mapToEntity(ingredientDto);
        ingredientService.updateIngredient(ingredientId,saveIngredient);

        return "redirect:/admin/ingredient";
    }
}
