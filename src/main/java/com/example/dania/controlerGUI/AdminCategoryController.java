package com.example.dania.controlerGUI;

import com.example.dania.dto.CategoryDto;
import com.example.dania.entity.Category;
import com.example.dania.mapper.CategoryMapper;
import com.example.dania.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/admin/category")
    public String home(Model model){
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories",allCategories);
        model.addAttribute("newCategory", new CategoryDto());
        model.addAttribute("updateCategory",new CategoryDto());
        return "admincategory";
    }

    @PostMapping("/admin/category/add-category")
    public String addCategory(@ModelAttribute("newCategory") CategoryDto categoryDto){
        Category category = categoryMapper.mapToEntity(categoryDto);
        categoryService.createCategory(category);
        return "redirect:/admin/category";
    }

    @PostMapping("/admin/category/del-category")
    public String delCategory(@RequestParam(name = "id") Long catId){
        categoryService.deleteCategory(catId);

        return "redirect:/admin/category";
    }

    @PostMapping("/admin/category/update-category")
    public String updateCategory(@RequestParam(name = "id") Long catId, @ModelAttribute("updateCategory") CategoryDto categoryDto){
        Category category = categoryMapper.mapToEntity(categoryDto);
        categoryService.updateCategory(catId,category);

        return "redirect:/admin/category";
    }
}
