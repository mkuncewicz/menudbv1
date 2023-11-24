package com.example.dania.mapper;

import com.example.dania.dto.DishDto;
import com.example.dania.entity.Dish;
import com.example.dania.entity.Ingredient;
import com.example.dania.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishMapper {

    @Autowired
    private CategoryService categoryService;

    public Dish mapToEntity(DishDto dishDto){

        Dish result = new Dish();

        result.setName(dishDto.getName());
        result.setCost(dishDto.getCost());
        result.setCategory(categoryService.getCategory(dishDto.getCategoryId()));
//        result.setIngredients();

        return result;
    }

    public DishDto mapToDto(Dish dish){

        DishDto dto = new DishDto();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCost(dish.getCost());
        dto.setCategoryId(dish.getCategory().getId());
        dto.setIngredient(dish.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet()));
        return dto;
    }

    public List<DishDto> mapToListDto(List<Dish> list){

        return list.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
