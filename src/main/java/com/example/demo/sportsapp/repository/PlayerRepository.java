package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<PlayerDTO> findAll();
    Optional<PlayerDTO> findById(Long id);
    PlayerDTO save(PlayerDTO playerDTO);
    PlayerDTO update(Long id, PlayerDTO playerDTO);
    void delete(Long id);
}
