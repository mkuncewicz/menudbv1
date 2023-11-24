package com.example.dania.service;

import com.example.dania.entity.CustomerOrder;
import com.example.dania.entity.Dish;
import com.example.dania.repository.CustomerOrderRepository;
import com.example.dania.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    public List<CustomerOrder> getAllOrders(){
        return (List<CustomerOrder>) orderRepository.findAll();
    }

    public CustomerOrder getOrder(Long orderId){
        Optional<CustomerOrder> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    public CustomerOrder createOrder(CustomerOrder order){
        return orderRepository.save(order);
    }

    public CustomerOrder updateOrder(Long orderId, CustomerOrder order){
        Optional<CustomerOrder> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()){
            CustomerOrder saveOrder = optionalOrder.get();
            saveOrder.setAddress(order.getAddress());
            return orderRepository.save(saveOrder);
        }
        return null;
    }

    public boolean deleteOrder(Long orderId){
        boolean isExist = orderRepository.existsById(orderId);
        if (isExist){
            orderRepository.deleteById(orderId);
        }
        return isExist;
    }

    public boolean addDishToOrder(Long orderId, Long dishId){
        Optional<CustomerOrder> customerOrderOptional = orderRepository.findById(orderId);
        Optional<Dish> dishOptional = dishRepository.findById(dishId);
        boolean exist = customerOrderOptional.isPresent() && dishOptional.isPresent();

        if(exist){
            CustomerOrder customerOrder = customerOrderOptional.get();
            Dish dish = dishOptional.get();

            customerOrder.getDishes().add(dish);
            dish.getOrders().add(customerOrder);

            customerOrder.setTotalCost(getTotalCost(customerOrder.getDishes()));

            orderRepository.save(customerOrder);
            dishRepository.save(dish);
        }
        return exist;
    }

    public boolean removeDishFromOrder(Long orderId, Long dishId){

        Optional<CustomerOrder> customerOrderOptional = orderRepository.findById(orderId);
        Optional<Dish> dishOptional = dishRepository.findById(dishId);
        boolean exist = customerOrderOptional.isPresent() && dishOptional.isPresent();
        if(exist){

            CustomerOrder customerOrder = customerOrderOptional.get();
            Dish dish = dishOptional.get();

            customerOrder.getDishes().remove(dish);
            dish.getOrders().remove(customerOrder);

            customerOrder.setTotalCost(getTotalCost(customerOrder.getDishes()));

            orderRepository.save(customerOrder);
            dishRepository.save(dish);
        }
        return exist;
    }

    private Float getTotalCost(List<Dish> list){

        List<Float> numbers = list.stream().map(Dish::getCost).toList();
        Float result = numbers.stream().reduce(0f,Float::sum);

        return roundOffNumber(result);
    }

    private Float roundOffNumber(Float number){
        BigDecimal bd = new BigDecimal(Float.toString(number));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}