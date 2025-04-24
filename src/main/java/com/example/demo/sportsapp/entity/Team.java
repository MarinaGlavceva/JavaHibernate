package com.example.demo.sportsapp.entity;

import lombok.Data;

@Data
public class Team {
    private Long id;
    private String name;
    private Long leagueId;
    private Long coachId;
}
