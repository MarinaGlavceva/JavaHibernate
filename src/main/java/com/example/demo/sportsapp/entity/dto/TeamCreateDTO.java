package com.example.demo.sportsapp.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TeamCreateDTO {

    @NotBlank(message = "Team name cannot be blank")
    private String name;

    @NotNull(message = "League ID is required")
    private Long leagueId;

    @NotNull(message = "Coach ID is required")
    private Long coachId;

    @NotEmpty(message = "List of player IDs cannot be empty")
    private List<Long> playerIds;
}
