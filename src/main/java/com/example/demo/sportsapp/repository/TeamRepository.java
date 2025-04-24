package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.dto.TeamDTO;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    Optional<TeamDTO> findById(Long id);
    List<TeamDTO> findAll();
    void save(TeamDTO teamDTO, Long leagueId, Long coachId, List<Long> playerIds);
    void update(TeamDTO teamDTO, Long leagueId, Long coachId, List<Long> playerIds);
    void delete(Long id);
}
