package com.example.dania.controlerGUI;

import com.example.dania.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAddController {

    @Autowired
    private DishService dishService;

    @GetMapping("/add")
    public String addDishToOrder(){

        return "adddish";
    }
}
