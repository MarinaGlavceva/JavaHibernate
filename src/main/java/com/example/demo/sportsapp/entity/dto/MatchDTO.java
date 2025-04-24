package com.example.demo.sportsapp.entity.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;


@Data
@Builder
public class MatchDTO {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private LocalDate date;
}
