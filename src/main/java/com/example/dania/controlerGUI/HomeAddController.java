package com.example.dania.controlerGUI;


import com.example.dania.dto.DishDto;
import com.example.dania.entity.Dish;
import com.example.dania.mapper.DishMapper;
import com.example.dania.service.DishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeAddController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    @GetMapping("/add/{catId}")
    public String addDishToOrder(Model model, @PathVariable Long catId){
        List<DishDto> dishDtoList = dishMapper.mapToListDto(dishService.getDishesFromCategory(catId));

        model.addAttribute("dishes",dishDtoList);

        return "adddish";
    }
}