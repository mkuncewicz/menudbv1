package com.example.dania.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {

    private Long id;
    private String name;
    private Float cost;
    private Set<Long> ingredient = new HashSet<>();
    private Long categoryId;
}
