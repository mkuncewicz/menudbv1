package com.example.dania.controler;

import com.example.dania.dto.DishDto;
import com.example.dania.entity.Dish;
import com.example.dania.mapper.DishMapper;
import com.example.dania.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/menu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DishControler {

    private final DishService dishService;

    private final DishMapper dishMapper;

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes(){
        return ResponseEntity.ok(dishMapper.mapToListDto(dishService.getAllDishes()));
    }

    @GetMapping("/{danieId}")
    public ResponseEntity<DishDto> getDish(@PathVariable Long danieId){

        Dish dish = dishService.getDish(danieId);

        if(dish != null){
            DishDto result = dishMapper.mapToDto(dish);
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/image/{dishId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long dishId){
        Dish dish = dishService.getDish(dishId);
        byte[] imageData = dish.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishDto> createDish(@RequestBody DishDto danie){

        Dish save = dishService.createDish(dishMapper.mapToEntity(danie));


        return ResponseEntity.ok(dishMapper.mapToDto(save));
    }

    @PostMapping("/{danieId}/{skladnikId}")
    public ResponseEntity<Void> addIngredient(@PathVariable Long danieId, @PathVariable Long skladnikId){

        boolean check = dishService.addIngredientToDish(danieId, skladnikId);

        if (check) return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{danieId}/{skladnikId}")
    public ResponseEntity<Void> delIngredient(@PathVariable Long danieId, @PathVariable Long skladnikId){

        boolean check = dishService.removeIngredientFromDish(danieId, skladnikId);

        if (check) return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{danieId}")
    public ResponseEntity<DishDto> updateDish(@RequestBody DishDto dishDto, @PathVariable Long danieId){

        Dish dish = dishService.updateDish(danieId, dishMapper.mapToEntity(dishDto));


        return ResponseEntity.ok(dishMapper.mapToDto(dish));
    }

    @DeleteMapping("/{danieId}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long danieId){

        dishService.deleteDish(danieId);
        return ResponseEntity.ok().build();
    }
}
