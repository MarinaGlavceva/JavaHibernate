package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.Player;
import com.example.demo.sportsapp.entity.dto.PlayerDTO;
import com.example.demo.sportsapp.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> resultList = new ArrayList<>();

        for (Player player : players) {
            resultList.add(PlayerDTO.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .teamId(player.getTeam().getId())
                    .build());
        }
        return resultList;
    }

    public PlayerDTO getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);

        return PlayerDTO.builder()
                .id(player.get().getId())
                .name(player.get().getName())
                .teamId(player.get().getTeam().getId())
                .build();
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setAge(playerDTO.getAge());
        playerRepository.save(player);
        return convertToDTO(player);
    }

    private PlayerDTO convertToDTO(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .name(player.getName())
                .build();
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = new Player();
        if (playerRepository.findById(id).isPresent()) {
            player = playerRepository.findById(id).get();
        }
        player.setName(playerDTO.getName());
        player.setAge(playerDTO.getAge());
        playerRepository.save(player);
        return convertToDTO(player);
    }

    public void deletePlayer(Long id) {
        if (playerRepository.findById(id).isPresent()) {
            playerRepository.deleteById(id);
        }
    }
}