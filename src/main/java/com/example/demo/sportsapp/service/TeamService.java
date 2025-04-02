package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.Team;
import com.example.demo.sportsapp.entity.dto.TeamDTO;
import com.example.demo.sportsapp.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> resultList = new ArrayList<>();
        for (Team team : teams) {
            resultList.add(TeamDTO.builder()
                    .id(team.getId())
                    .name(team.getName())
                    .leagueId(team.getLeague().getId())
                    .build());
        }
        return resultList;
    }

    public TeamDTO getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);

        return TeamDTO.builder()
                .id(team.get().getId())
                .name(team.get().getName())
                .leagueId(team.get().getLeague().getId())
                .build();
    }

    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        teamRepository.save(team);
        return convertToDTO(team);
    }
    private TeamDTO convertToDTO(Team team) {

        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
    }

    public TeamDTO updateTeam(Long id, TeamDTO teamDTO) {
        Team team = new Team();
        if (teamRepository.findById(id).isPresent()) {
            team = teamRepository.findById(id).get();
        }
        team.setName(teamDTO.getName());
        teamRepository.save(team);
        return convertToDTO(team);
    }

    public void deleteTeam(Long id) {
        if (teamRepository.findById(id).isPresent()) {
            teamRepository.deleteById(id);
        }
    }

   }
