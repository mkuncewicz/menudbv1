package com.example.dania.controlerGUI;

import com.example.dania.dto.DishDto;
import com.example.dania.entity.Category;
import com.example.dania.entity.Dish;
import com.example.dania.mapper.DishMapper;
import com.example.dania.service.CategoryService;
import com.example.dania.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminDishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    public String home(Model model){
        List<Dish> dishList = dishService.getAllDishes();
        List<Category> categoryLit = categoryService.getAllCategories();

        model.addAttribute("dishes",dishList);
        model.addAttribute("newDish", new DishDto());
        model.addAttribute("updateDish",new DishDto());
        model.addAttribute("categories",categoryLit);

        return "home1";
    }

    @PostMapping("/add-dish-2")
    public String addDish(@ModelAttribute("newDish") DishDto dishDto){
        dishDto.setCategoryId(1L); //Do usuniecia
        Dish saveDish = dishMapper.mapToEntity(dishDto);
        dishService.createDish(saveDish);
        return "redirect:/admin";
    }

    @PostMapping("/delete-dish-2")
    public String deleteDish(@RequestParam(name = "id") Long dishId){
        dishService.deleteDish(dishId);
        return "redirect:/admin";
    }

    @PostMapping("/update-dish-2")
    public String updateDish(@RequestParam(name = "id") Long dishId, @ModelAttribute("updateDish") DishDto dishDto){
        dishDto.setCategoryId(1L); //Do usuniecia
        if (dishDto.getName() != null && dishDto.getName().isEmpty()) dishDto.setName(null);
        Dish updateDist = dishMapper.mapToEntity(dishDto);
        dishService.updateDish(dishId,updateDist);

        return "redirect:/admin";
    }


}
