package com.footballleague.footballleague.mapper;

import com.footballleague.footballleague.dto.PlayerStatsDto;
import com.footballleague.footballleague.entity.Player;
import com.footballleague.footballleague.entity.PlayerStats;
import com.footballleague.footballleague.repository.PlayerRepository;

import jakarta.persistence.EntityNotFoundException;


public class PlayerStatsMapper {

    public static PlayerStatsDto toDto(PlayerStats playerStats){
        PlayerStatsDto dto = new PlayerStatsDto();
        dto.setId(playerStats.getId());
        dto.setPlayerId(playerStats.getPlayer().getId());
        dto.setGoals(playerStats.getGoals());
        dto.setAssists(playerStats.getAssists());
        dto.setReds(playerStats.getReds());
        dto.setYellows(playerStats.getYellows());
        return dto;
    }

    public static PlayerStats toEntity(PlayerStatsDto playerStatsDto, PlayerRepository playerRepository){
        PlayerStats playerStats = new PlayerStats();
        playerStats.setId(playerStatsDto.getId());
        playerStats.setGoals(playerStatsDto.getGoals());
        playerStats.setAssists(playerStatsDto.getAssists());
        playerStats.setYellows(playerStatsDto.getYellows());
        playerStats.setReds(playerStatsDto.getReds());
        return playerStats;
    }

}
