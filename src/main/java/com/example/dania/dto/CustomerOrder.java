package com.example.dania.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    private Long id;

    private Float totalCost;

    private String address;

    private List<Long> dishes;
}
