package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDTO {
    // Геттеры и сеттеры
    private Long id;
    private String name;
    private int age;
    private Long teamId; // ID команды, к которой принадлежит игрок
}
