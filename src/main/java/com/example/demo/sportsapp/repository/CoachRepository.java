package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.dto.CoachDTO;

import java.util.List;
import java.util.Optional;

public interface CoachRepository {
    List<CoachDTO> findAll();
    Optional<CoachDTO> findById(Long id);
    CoachDTO save(CoachDTO dto);
    CoachDTO update(Long id, CoachDTO dto);
    void delete(Long id);
}
