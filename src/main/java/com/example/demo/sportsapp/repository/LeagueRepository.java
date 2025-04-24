package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.dto.LeagueDTO;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
    List<LeagueDTO> findAll();
    Optional<LeagueDTO> findById(Long id);
    LeagueDTO save(LeagueDTO leagueDTO);
    LeagueDTO update(Long id, LeagueDTO leagueDTO);
    void delete(Long id);
}
