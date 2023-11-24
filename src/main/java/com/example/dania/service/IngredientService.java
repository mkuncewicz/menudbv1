package com.example.dania.service;

import com.example.dania.entity.Ingredient;
import com.example.dania.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients(){
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    public Ingredient getIngredient(Long ingredientId){
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);
        return optionalIngredient.orElse(null);
    }

    public Ingredient createIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long ingredientId, Ingredient ingredient){

        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);

        if(optionalIngredient.isPresent()){
            Ingredient saveIngredient = optionalIngredient.get();
            saveIngredient.setName(ingredient.getName());
            saveIngredient.setAmount(ingredient.getAmount());
            return ingredientRepository.save(saveIngredient);
        }
        return null;
    }

    public boolean deleteIngredient(Long ingredientId){
        boolean isExist = ingredientRepository.existsById(ingredientId);

        if(isExist){
            ingredientRepository.deleteById(ingredientId);
        }
        return isExist;
    }
}
