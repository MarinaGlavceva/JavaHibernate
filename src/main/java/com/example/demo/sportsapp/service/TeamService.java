package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.dto.TeamDTO;
import com.example.demo.sportsapp.entity.dto.TeamCreateDTO;
import com.example.demo.sportsapp.repository.*;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final CoachRepository coachRepository;
    private final PlayerRepository playerRepository;

    public void createTeam(TeamCreateDTO dto) {
        leagueRepository.findById(dto.getLeagueId()).orElseThrow(() ->
                new IllegalArgumentException("League with ID " + dto.getLeagueId() + " not found"));

        coachRepository.findById(dto.getCoachId()).orElseThrow(() ->
                new IllegalArgumentException("Coach with ID " + dto.getCoachId() + " not found"));

        for (Long playerId : dto.getPlayerIds()) {
            playerRepository.findById(playerId).orElseThrow(() ->
                    new IllegalArgumentException("Player with ID " + playerId + " not found"));
        }

        teamRepository.save(
                com.example.demo.sportsapp.entity.dto.TeamDTO.builder()
                        .name(dto.getName())
                        .build(),
                dto.getLeagueId(),
                dto.getCoachId(),
                dto.getPlayerIds()
        );
    }

    public TeamDTO getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Team with ID " + id + " not found"));
    }
}
