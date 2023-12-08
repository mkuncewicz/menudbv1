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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminDishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/dish")
    public String home(Model model){
        List<Dish> dishList = dishService.getAllDishes();
        List<Category> categoryLit = categoryService.getAllCategories();

        model.addAttribute("dishes",dishList);
        model.addAttribute("newDish", new DishDto());
        model.addAttribute("updateDish",new DishDto());
        model.addAttribute("categories",categoryLit);

        return "admindish";
    }

    @GetMapping("admin/dishesByCategory")
    @ResponseBody
    public List<DishDto> getDishesByCategory(@RequestParam(name = "categoryId") Long categoryId){
        List<Dish> dishList = dishService.getDishesFromCategory(categoryId);

        return dishMapper.mapToListDto(dishList);
    }

    @PostMapping("admin/add-dish-2")
    public String addDish(@ModelAttribute("newDish") DishDto dishDto){
        dishDto.setCategoryId(1L); //Do usuniecia
        Dish saveDish = dishMapper.mapToEntity(dishDto);
        dishService.createDish(saveDish);
        return "redirect:/admin/dish";
    }

    @PostMapping("admin/add-dish-3")
    public String createNewDish(@ModelAttribute("newDish") DishDto dishDto,@RequestParam("image") MultipartFile file) throws IOException {
        dishDto.setCategoryId(1L);
        Dish saveDish = dishMapper.mapToEntity(dishDto);
//        saveDish.setId(catId);
        saveDish.setImage(file.getBytes());
        dishService.createDish(saveDish);
        return "redirect:/admin/dish";
    }

    @PostMapping("admin/delete-dish-2")
    public String deleteDish(@RequestParam(name = "id") Long dishId){
        dishService.deleteDish(dishId);
        return "redirect:/admin/dish";
    }

    @PostMapping("admin/update-dish-2")
    public String updateDish(@RequestParam(name = "id") Long dishId, @ModelAttribute("updateDish") DishDto dishDto){
        dishDto.setCategoryId(1L); //Do usuniecia
        if (dishDto.getName() != null && dishDto.getName().isEmpty()) dishDto.setName(null);
        Dish updateDist = dishMapper.mapToEntity(dishDto);
        dishService.updateDish(dishId,updateDist);

        return "redirect:/admin/dish";
    }

    @PostMapping("admin/update-dish-3")
    public String updateDish2(@RequestParam(name = "id") Long dishId, @ModelAttribute("updateDish") DishDto dishDto,@RequestParam("image")MultipartFile file) throws IOException {
        dishDto.setCategoryId(1L); //Do usuniecia
        if (dishDto.getName() != null && dishDto.getName().isEmpty()) dishDto.setName(null);
        Dish updateDist = dishMapper.mapToEntity(dishDto);
        updateDist.setImage(file.getBytes());
        dishService.updateDish(dishId,updateDist);

        return "redirect:/admin/dish";
    }
}