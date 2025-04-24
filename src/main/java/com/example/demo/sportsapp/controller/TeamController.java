package com.example.demo.sportsapp.controller;

import com.example.demo.sportsapp.entity.dto.TeamDTO;
import com.example.demo.sportsapp.entity.dto.TeamCreateDTO;
import com.example.demo.sportsapp.service.TeamService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor

public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestBody @Valid TeamCreateDTO dto) {
        teamService.createTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable Long id) {
        TeamDTO dto = teamService.getTeamById(id);
        return ResponseEntity.ok(dto);
    }

}

