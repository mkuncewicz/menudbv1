package com.example.dania.controlerGUI;

import com.example.dania.dto.CategoryDto;
import com.example.dania.dto.CustomerOrderDto;
import com.example.dania.dto.DishDto;
import com.example.dania.entity.CustomerOrder;
import com.example.dania.mapper.CategoryMapper;
import com.example.dania.mapper.CustomerOrderMapper;
import com.example.dania.mapper.DishMapper;
import com.example.dania.service.CategoryService;
import com.example.dania.service.CustomerOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerOrderMapper customerOrderMapper;

   @Autowired
   private DishMapper dishMapper;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        List<CategoryDto> categoryDtoList = categoryMapper.mapToListDto(categoryService.getAllCategories());
        model.addAttribute("categories",categoryDtoList);

        List<DishDto> itemsOrder = dishMapper.mapToListDto(customerOrderService.getOrder(1L).getDishes());
        model.addAttribute("orderItems",itemsOrder);

        CustomerOrder customerOrder = customerOrderService.getOrder(1L);
        model.addAttribute("orderItems2", customerOrder);

        return "home";
    }
}