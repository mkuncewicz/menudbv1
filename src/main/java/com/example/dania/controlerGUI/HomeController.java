package com.example.dania.controlerGUI;

import com.example.dania.dto.DishDto;
import com.example.dania.entity.Dish;
import com.example.dania.entity.Ingredient;
import com.example.dania.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private DishRepository dishRepository;
    private List<Dish> dishList = new ArrayList<>();

    private List<Ingredient> ingredientList = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model){
        dishList = (List<Dish>) dishRepository.findAll();
        model.addAttribute("dishes", dishList);
        model.addAttribute("newDish",new DishDto());
        model.addAttribute("updateDish",new DishDto());
        return "home";
    }


    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute("newDish") DishDto dishDto){
        Dish saveDish = new Dish();
        saveDish.setName(dishDto.getName());
        dishRepository.save(saveDish);
        return "redirect:/";
    }

    @PostMapping("/delete-dish")
    public String deleteDish(@RequestParam(name = "id") Long dishId){
        if(dishRepository.existsById(dishId)) {
            dishRepository.deleteById(dishId);
        }
        return "redirect:/";
    }

    @PostMapping("/update-dish")
    public String updateDish(@RequestParam(name = "id") Long dishId, @ModelAttribute("updateDish") DishDto dishDto){

        Optional<Dish> optionalDish = dishRepository.findById(dishId);

        if(optionalDish.isPresent()){
            Dish saveDish = optionalDish.get();
            saveDish.setName(dishDto.getName());
            dishRepository.save(saveDish);
        }

        return "redirect:/";
    }
}
