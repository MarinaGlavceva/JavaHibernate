package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoachDTO {
    private Long id;
    private String name;
    private Long teamId; // ID команды, которой он тренирует
}
