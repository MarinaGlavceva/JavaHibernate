package com.example.demo.sportsapp.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Match {
    private Long id;
    private LocalDate date;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long leagueId;
}
