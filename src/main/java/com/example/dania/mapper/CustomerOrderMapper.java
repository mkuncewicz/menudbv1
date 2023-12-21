package com.example.dania.mapper;

import com.example.dania.dto.CustomerOrderDto;
import com.example.dania.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderMapper {

    public com.example.dania.entity.CustomerOrder mapToEntity(CustomerOrderDto orderDto){
        com.example.dania.entity.CustomerOrder order = new com.example.dania.entity.CustomerOrder();
        order.setId(orderDto.getId());
        order.setAddress(orderDto.getAddress());
        order.setTotalCost(orderDto.getTotalCost());
        return order;
    }

    public CustomerOrderDto mapToDto(com.example.dania.entity.CustomerOrder order){
        CustomerOrderDto orderDto = new CustomerOrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setAddress(order.getAddress());
        orderDto.setDishes(order.getDishes().stream().map(Dish::getId).collect(Collectors.toList()));
        return orderDto;
    }

    public List<CustomerOrderDto> mapToListDto (List<com.example.dania.entity.CustomerOrder> orders){
        return orders.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
