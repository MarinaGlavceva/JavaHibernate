package com.example.demo.sportsapp.repository;

import com.example.demo.sportsapp.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
