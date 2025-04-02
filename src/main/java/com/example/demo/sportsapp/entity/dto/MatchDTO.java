package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchDTO {

    private Long id;
    private Long homeTeamId;  // ID домашней команды
    private Long awayTeamId;  // ID гостевой команды
    private String date;  // Дата матча
}