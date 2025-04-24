package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamDTO {
    private Long id;
    private String name;

    private LeagueDTO league;         // Вложенная лига
    private CoachDTO coach;           // Вложенный тренер
    private List<PlayerDTO> players;  // Вложенный список игроков
}
