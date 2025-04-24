package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.dto.PlayerDTO;
import com.example.demo.sportsapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll();
    }

    public PlayerDTO getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Player with ID " + id + " not found"));
    }

    public PlayerDTO createPlayer(PlayerDTO dto) {
        return playerRepository.save(dto);
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO dto) {
        if (playerRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Player with ID " + id + " not found");
        }
        return playerRepository.update(id, dto);
    }

    public void deletePlayer(Long id) {
        playerRepository.delete(id);
    }
}
