package com.footballleague.footballleague.service;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.entity.TeamDetails;

import java.util.List;

public interface PlayerService {
    PlayerDto getPlayerById(Long playerId);
    List<PlayerDto> getAllPlayers();
    PlayerDto updatePlayer(Long playerId, PlayerDto updatedPlayer);
    void deletePlayer(Long playerId);
    List<PlayerDto> getPlayersByTeam(TeamDetails teamDetails);
    void addPlayersToTeam(TeamDetails teamDetails, List<PlayerDto> playerDtoList);
}
