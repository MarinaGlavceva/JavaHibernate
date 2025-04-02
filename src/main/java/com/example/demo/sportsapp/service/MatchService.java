package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.Match;
import com.example.demo.sportsapp.entity.dto.MatchDTO;
import com.example.demo.sportsapp.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        List<MatchDTO> resultList = new ArrayList<>();
        for (Match match : matches) {
            resultList.add(MatchDTO.builder()
                    .id(match.getId())
                    .homeTeamId(match.getHomeTeam().getId())
                    .awayTeamId(match.getAwayTeam().getId())
                    .build());
        }
        return resultList;
    }

    public MatchDTO getMatchById(Long id) {
        Optional<Match> match = matchRepository.findById(id);

        return MatchDTO.builder()
                .id(match.get().getId())
                .homeTeamId(match.get().getHomeTeam().getId())
                .awayTeamId(match.get().getAwayTeam().getId())
                .build();
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = new Match();
        matchRepository.save(match);
        return convertToDTO(match);
    }

    private MatchDTO convertToDTO(Match match) {

        return MatchDTO.builder()
                .id(match.getId())
                .homeTeamId(match.getHomeTeam().getId())
                .awayTeamId(match.getAwayTeam().getId())
                .build();
    }



    public MatchDTO updateMatch(Long id, MatchDTO matchDTO) {
        Match match = new Match();
        if (matchRepository.findById(id).isPresent()) {
            match = matchRepository.findById(id).get();
        }
        matchRepository.save(match);
        return convertToDTO(match);
    }

    public void deleteMatch(Long id) {
        if (matchRepository.findById(id).isPresent()) {
            matchRepository.deleteById(id);
        }
    }

}
