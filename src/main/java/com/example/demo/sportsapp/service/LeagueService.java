package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.League;
import com.example.demo.sportsapp.entity.dto.LeagueDTO;
import com.example.demo.sportsapp.repository.LeagueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<LeagueDTO> getAllLeagues() {
        List<League> leagues = leagueRepository.findAll();
        List<LeagueDTO> resultList = new ArrayList<>();
        for (League league : leagues) {
            resultList.add(LeagueDTO.builder()
                    .id(league.getId())
                    .name(league.getName())
                    .build());
        }
        return resultList;
    }

    public LeagueDTO getLeagueById(Long id) {
        Optional<League> league = leagueRepository.findById(id);

        return LeagueDTO.builder()
                .id(league.get().getId())
                .name(league.get().getName())
                .build();
    }

    public LeagueDTO createLeague(LeagueDTO leagueDTO) {
        League league = new League();
        league.setName(leagueDTO.getName());
        leagueRepository.save(league);
        return convertToDTO(league);
    }

    private LeagueDTO convertToDTO(League league) {

        return LeagueDTO.builder()
                .id(league.getId())
                .name(league.getName())
                .build();
    }

    public LeagueDTO updateLeague(Long id, LeagueDTO leagueDTO) {
        League league = new League();
        if (leagueRepository.findById(id).isPresent()) {
            league = leagueRepository.findById(id).get();
        }
        league.setName(leagueDTO.getName());
        leagueRepository.save(league);
        return convertToDTO(league);
    }

    public void deleteLeague(Long id) {
        if (leagueRepository.findById(id).isPresent()) {
            leagueRepository.deleteById(id);
        }
    }

}