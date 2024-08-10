package com.footballleague.footballleague.repository;

import com.footballleague.footballleague.entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    List<PlayerStats> findByPlayerId(Long playerId);
}
