package com.footballleague.footballleague.service;

import com.footballleague.footballleague.dto.PlayerStatsDto;

import java.util.List;

public interface PlayerStatsService {
    PlayerStatsDto createPlayerStats(Long playerId, PlayerStatsDto playerStatsDto);
    PlayerStatsDto getPlayerStats(Long playerId);
    PlayerStatsDto updatePlayerStats(Long playerId, PlayerStatsDto playerStatsDto);
    List<PlayerStatsDto> getAllPlayerStats();
    List<PlayerStatsDto> getSortedPlayerStatsByGoals();
    List<PlayerStatsDto> getSortedPlayerStatsByAssists();
    List<PlayerStatsDto> getSortedPlayerStatsByReds();
    List<PlayerStatsDto> getSortedPlayerStatsByYellows();
    void deletePlayerStatsById(Long playerStatsId);
}
