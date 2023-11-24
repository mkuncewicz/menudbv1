package com.example.dania.service;

import com.example.dania.entity.Dish;
import com.example.dania.entity.Ingredient;
import com.example.dania.repository.DishRepository;
import com.example.dania.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Dish> getAllDishes(){
        return (List<Dish>) dishRepository.findAll();
    }

    public Dish getDish(Long dishId){
        Optional<Dish> result = dishRepository.findById(dishId);
        return result.orElse(null);
    }

    public Dish createDish(Dish dish){
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long dishId,Dish updateDish){

        Optional<Dish> dishOptional = dishRepository.findById(dishId);

        if(dishOptional.isPresent()){
            Dish saveDish = dishOptional.get();
            saveDish.setName(updateDish.getName());
            saveDish.setCategory(updateDish.getCategory());
            return dishRepository.save(saveDish);
        }
        return null;
    }

    public boolean deleteDish(Long dishId){

        boolean isExist = dishRepository.existsById(dishId);
        if (isExist){
            dishRepository.deleteById(dishId);
        }
        return isExist;
    }

    public boolean addIngredientToDish(Long dishId, Long ingredientId){

        Optional<Dish> dishOptional = dishRepository.findById(dishId);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);

        boolean exist = dishOptional.isPresent() && ingredientOptional.isPresent();

        if (exist){
            Dish dish = dishOptional.get();
            Ingredient ingredient = ingredientOptional.get();

            dish.getIngredients().add(ingredient);
            ingredient.getDishes().add(dish);

            dishRepository.save(dish);
            ingredientRepository.save(ingredient);
        }

        return exist;
    }

    public boolean removeIngredientFromDish(Long dishId, Long ingredientId){

        Optional<Dish> dishOptional = dishRepository.findById(dishId);
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);

        boolean exist = dishOptional.isPresent() && ingredientOptional.isPresent();

        if (exist){
            Dish dish = dishOptional.get();
            Ingredient ingredient = ingredientOptional.get();

            dish.getIngredients().remove(ingredient);
            ingredient.getDishes().remove(dish);

            dishRepository.save(dish);
            ingredientRepository.save(ingredient);
        }
        return exist;
    }
}