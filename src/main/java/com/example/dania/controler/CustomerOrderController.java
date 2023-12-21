package com.example.dania.controler;

import com.example.dania.dto.CustomerOrderDto;
import com.example.dania.mapper.CustomerOrderMapper;
import com.example.dania.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    private final CustomerOrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<CustomerOrderDto>> getAllOrders(){

        return ResponseEntity.ok(orderMapper.mapToListDto(orderService.getAllOrders()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomerOrderDto> getOrder(@PathVariable Long orderId) {

        com.example.dania.entity.CustomerOrder order = orderService.getOrder(orderId);

        if (order != null) {
            return ResponseEntity.ok(orderMapper.mapToDto(order));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerOrderDto> createOrder(@RequestBody CustomerOrderDto orderDto){

        return ResponseEntity.ok(orderMapper.mapToDto(orderService.createOrder(orderMapper.mapToEntity(orderDto))));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<CustomerOrderDto> updateOrder(@PathVariable Long orderId, @RequestBody CustomerOrderDto orderDto){
        com.example.dania.entity.CustomerOrder saveOrder = orderService.updateOrder(orderId,orderMapper.mapToEntity(orderDto));

        if(saveOrder != null){
            return ResponseEntity.ok(orderMapper.mapToDto(saveOrder));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){

        boolean check = orderService.deleteOrder(orderId);

        if (check){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{orderId}/{dishId}")
    public ResponseEntity<Void> addDishToOrder(@PathVariable Long orderId, @PathVariable Long dishId){

        boolean check = orderService.addDishToOrder(orderId,dishId);

        if (check) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderId}/{dishId}")
    public ResponseEntity<Void> deleteDishFromOrder(@PathVariable Long orderId, @PathVariable Long dishId){

        boolean check = orderService.removeDishFromOrder(orderId, dishId);

        if (check) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
