package com.example.dania.mapper;

import com.example.dania.dto.CategoryDto;
import com.example.dania.entity.Category;
import com.example.dania.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMapper {

    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDishes(
                category.getDishes().stream().map(Dish::getId).collect(Collectors.toSet())
        );
        return categoryDto;
    }

    public List<CategoryDto> mapToListDto(List<Category> list){
        return list.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}