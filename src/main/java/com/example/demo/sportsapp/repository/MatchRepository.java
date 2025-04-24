package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.dto.MatchDTO;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    List<MatchDTO> findAll();
    Optional<MatchDTO> findById(Long id);
    MatchDTO save(MatchDTO dto);
    MatchDTO update(Long id, MatchDTO dto);
    void delete(Long id);
}
