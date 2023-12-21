package com.example.dania.controlerGUI;

import com.example.dania.dto.CategoryDto;
import com.example.dania.entity.Category;
import com.example.dania.mapper.CategoryMapper;
import com.example.dania.service.CategoryService;
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
    @GetMapping("/")
    public String home(Model model) {
        List<CategoryDto> categoryDtoList = categoryMapper.mapToListDto(categoryService.getAllCategories());
        model.addAttribute("categories",categoryDtoList);
        return "home";
    }
}