package com.footballleague.footballleague.service.impl;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.entity.Player;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.PlayerMapper;
import com.footballleague.footballleague.repository.PlayerRepository;
import com.footballleague.footballleague.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;
    @Override
    public PlayerDto getPlayerById(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + playerId));
        return PlayerMapper.mapToPlayerDto(player);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(PlayerMapper::mapToPlayerDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerDto updatedPlayer) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + playerId));
        existingPlayer.setPlayerName(updatedPlayer.getPlayerName());
        existingPlayer.setPlayerPosition(updatedPlayer.getPlayerPosition());
        Player savedPlayer = playerRepository.save(existingPlayer);
        return PlayerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public void deletePlayer(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new ResourceNotFoundException("Player not found with ID: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<PlayerDto> getPlayersByTeam(TeamDetails teamDetails) {
        List<Player> players = playerRepository.findByTeamDetails(teamDetails);
        return players.stream()
                .map(PlayerMapper::mapToPlayerDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addPlayersToTeam(TeamDetails teamDetails, List<PlayerDto> playerDtoList) {
        List<Player> players = playerDtoList.stream()
                .map(PlayerMapper::mapToPlayer)
                .collect(Collectors.toList());
        for (Player player : players) {
            player.setTeamDetails(teamDetails);
        }
        playerRepository.saveAll(players);
    }
}
