package com.example.dania.controlerGUI;


import com.example.dania.dto.DishDto;
import com.example.dania.entity.CustomerOrder;
import com.example.dania.mapper.DishMapper;
import com.example.dania.service.CustomerOrderService;
import com.example.dania.service.DishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeAddController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/add/{catId}")
    public String homeSelect(Model model, @PathVariable Long catId, HttpSession session){
        List<DishDto> dishDtoList = dishMapper.mapToListDto(dishService.getDishesFromCategory(catId));
        model.addAttribute("dishes",dishDtoList);

        List<DishDto> itemsOrder = dishMapper.mapToListDto(customerOrderService.getOrder(1L).getDishes());
        model.addAttribute("orderItems",itemsOrder);

        CustomerOrder customerOrder = customerOrderService.getOrder(1L);
        model.addAttribute("orderItems2", customerOrder);

        model.addAttribute("cat",catId);


        return "adddish";
    }

    @PostMapping("/add/{catId}/plus/{orderId}/{dishId}")
    public String addDishToOrder(@PathVariable Long catId, @PathVariable Long orderId, @PathVariable Long dishId){

        customerOrderService.addDishToOrder(orderId,dishId);

        return "redirect:/add/" + catId;
    }

    @GetMapping("/add/{catId}/remove/{orderId}/{dishId}")
    public String removeDishToOrder(@PathVariable Long catId, @PathVariable Long orderId, @PathVariable Long dishId){

        customerOrderService.removeDishFromOrder(orderId,dishId);

        return  "redirect:/add/" + catId;
    }
}