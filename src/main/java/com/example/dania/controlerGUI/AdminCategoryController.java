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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/admin/category/add-category2")
    public String addCategory2(@ModelAttribute("newCategory")CategoryDto categoryDto, @RequestParam("image")MultipartFile file) throws IOException {
        Category saveCategory = categoryMapper.mapToEntity(categoryDto);
        saveCategory.setImage(file.getBytes());
        categoryService.createCategory(saveCategory);
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

    @PostMapping("/admin/category/update-category2")
    public String updateCategory2(@RequestParam(name = "id")Long catId, @ModelAttribute("updateUsers") CategoryDto categoryDto, @RequestParam("image")MultipartFile file) throws IOException {
        Category category = categoryMapper.mapToEntity(categoryDto);
        category.setImage(file.getBytes());
        categoryService.updateCategory(catId,category);

        return "redirect:/admin/category";

    }

    @PostMapping("/admin/category/addImage")
    public String addImageCategory(@RequestParam(name = "id")Long catId, @RequestParam("image")MultipartFile file) throws IOException {
        byte[] saveImage = file.getBytes();
        Category saveCategory = new Category();
        saveCategory.setImage(saveImage);
        categoryService.updateCategory(catId, saveCategory);

        return "redirect:/admin/category";
    }
}
