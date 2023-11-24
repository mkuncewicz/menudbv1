package com.example.dania.mapper;

import com.example.dania.dto.IngredientDto;
import com.example.dania.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientMapper {


    public Ingredient mapToEntity(IngredientDto ingredientDto) {

        Ingredient result = new Ingredient();

        result.setName(ingredientDto.getName());
        result.setAmount(ingredientDto.getAmount());
        return result;
    }

    public IngredientDto mapToDto(Ingredient ingredient) {

        IngredientDto dto = new IngredientDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setAmount(ingredient.getAmount());
        return dto;
    }

    public List<IngredientDto> mapToListDto(List<Ingredient> list) {

        return list.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
