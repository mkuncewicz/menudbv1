package com.example.dania.service;

import com.example.dania.entity.Category;
import com.example.dania.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public boolean categoryExist(Long categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public List<Category> getAllCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId){
        Optional<Category> result = categoryRepository.findById(categoryId);
        return result.orElse(null);
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category updateCategory){
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isPresent()){
            Category saveCategory = categoryOptional.get();
            if (updateCategory.getName() != null && !updateCategory.getName().isEmpty())saveCategory.setName(updateCategory.getName());
            if (updateCategory.getImage() != null && updateCategory.getImage().length > 0) saveCategory.setImage(updateCategory.getImage());
            return categoryRepository.save(saveCategory);
        }
            return null;
    }

    public boolean deleteCategory(Long categoryId){
        boolean isExist = categoryRepository.existsById(categoryId);
        if(isExist){
            categoryRepository.deleteById(categoryId);
        }
        return isExist;
    }
}
