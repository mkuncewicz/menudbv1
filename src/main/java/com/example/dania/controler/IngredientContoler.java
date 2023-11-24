package com.example.dania.controler;


import com.example.dania.dto.IngredientDto;
import com.example.dania.entity.Ingredient;
import com.example.dania.mapper.IngredientMapper;
import com.example.dania.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/menu/ingredient")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IngredientContoler {

    private final IngredientService ingredientService;

    private final IngredientMapper ingredientMapper;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients(){


        List<IngredientDto> result = ingredientMapper.mapToListDto(ingredientService.getAllIngredients());

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{skladnikId}")
    public ResponseEntity<IngredientDto> getIngredient (@PathVariable Long skladnikId){

        Ingredient ingredient = ingredientService.getIngredient(skladnikId);
        return ResponseEntity.ok(ingredientMapper.mapToDto(ingredient));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto skladniki){

        Ingredient save = ingredientService.createIngredient(ingredientMapper.mapToEntity(skladniki));

        return ResponseEntity.ok(ingredientMapper.mapToDto(save));
    }

    @PutMapping("/{skladnikId}")
    public ResponseEntity<IngredientDto> updateIngredient(@RequestBody IngredientDto ingredientDto, @PathVariable Long skladnikId){

        Ingredient ingredient = ingredientService.updateIngredient(skladnikId, ingredientMapper.mapToEntity(ingredientDto));

        return ResponseEntity.ok(ingredientMapper.mapToDto(ingredient));
    }


    @DeleteMapping("/{skladnikId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long skladnikId){

        ingredientService.deleteIngredient(skladnikId);

        return ResponseEntity.ok().build();
    }
}
