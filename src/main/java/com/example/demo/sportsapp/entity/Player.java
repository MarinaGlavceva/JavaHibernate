package com.example.demo.sportsapp.entity;

import lombok.Data;

@Data
public class Player {
    private Long id;
    private String name;
    private int age;
    private Long teamId; // team_id из базы
}
