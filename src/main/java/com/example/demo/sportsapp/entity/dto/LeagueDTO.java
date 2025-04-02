package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeagueDTO {
    private Long id;
    private String name;
}
