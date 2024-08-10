package com.footballleague.footballleague.service.impl;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.dto.PlayerStatsDto;
import com.footballleague.footballleague.entity.Player;
import com.footballleague.footballleague.entity.PlayerStats;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.PlayerMapper;
import com.footballleague.footballleague.mapper.PlayerStatsMapper;
import com.footballleague.footballleague.repository.PlayerRepository;
import com.footballleague.footballleague.repository.PlayerStatsRepository;
import com.footballleague.footballleague.service.PlayerStatsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerStatsServiceImpl implements PlayerStatsService {
   private PlayerStatsRepository playerStatsRepository;
   private PlayerRepository playerRepository;

    @Override
    public PlayerStatsDto createPlayerStats(Long playerId, PlayerStatsDto playerStatsDto) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with ID: " + playerId));

        PlayerStats playerStats = PlayerStatsMapper.toEntity(playerStatsDto, playerRepository);
        playerStats.setPlayer(player);

        PlayerStats savedPlayerStats = playerStatsRepository.save(playerStats);
        return PlayerStatsMapper.toDto(savedPlayerStats);
    }

    @Override
    public PlayerStatsDto getPlayerStats(Long playerId) {
        List<PlayerStats> playerStatsList = playerStatsRepository.findByPlayerId(playerId);

        // Check if the list is not empty
        if (!playerStatsList.isEmpty()) {
            // Retrieve the first PlayerStats entity from the list
            PlayerStats playerStats = playerStatsList.get(0);

            // Retrieve player name and position from the associated player entity
            String playerName = playerStats.getPlayer().getPlayerName();
            String playerPosition = playerStats.getPlayer().getPlayerPosition();

            // Create a new PlayerStatsDto object with player name, position, and other stats
            PlayerStatsDto playerStatsDto = PlayerStatsMapper.toDto(playerStats);
            playerStatsDto.setPlayerName(playerName);
            playerStatsDto.setPlayerPosition(playerPosition);

            return playerStatsDto;
        } else {
            // If no player stats are found, return null or throw an exception as per your requirement
            return null;
        }
    }

    @Override
    public PlayerStatsDto updatePlayerStats(Long playerId, PlayerStatsDto playerStatsDto) {
        List<PlayerStats> optionalPlayerStats = playerStatsRepository.findByPlayerId(playerId);

        if (!optionalPlayerStats.isEmpty()) {
            // Get the existing player stats
            PlayerStats existingPlayerStats = optionalPlayerStats.get(0);

            // Update the player stats with the values from the updatedPlayerStatsDto
            existingPlayerStats.setGoals(playerStatsDto.getGoals());
            existingPlayerStats.setAssists(playerStatsDto.getAssists());
            existingPlayerStats.setYellows(playerStatsDto.getYellows());
            existingPlayerStats.setReds(playerStatsDto.getReds());

            // Save the updated player stats
            PlayerStats updatedPlayerStats = playerStatsRepository.save(existingPlayerStats);

            // Map the updated player stats entity to a DTO and return it
            return PlayerStatsMapper.toDto(updatedPlayerStats);
        } else {
            // If no player stats are found for the given player ID, you can throw an exception or handle it as needed
            throw new EntityNotFoundException("Player stats not found for player ID: " + playerId);
        }
    }


    @Override
    public List<PlayerStatsDto> getAllPlayerStats() {
        List<PlayerStats> playerStatsList = playerStatsRepository.findAll();
        return getPlayerStatsDtos(playerStatsList);
    }

    @Override
    public List<PlayerStatsDto> getSortedPlayerStatsByGoals() {
        List<PlayerStats> sortedPlayerStatsList = playerStatsRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(PlayerStats::getGoals).reversed())
                .toList();

        return getPlayerStatsDtos(sortedPlayerStatsList);
    }

    private List<PlayerStatsDto> getPlayerStatsDtos(List<PlayerStats> sortedPlayerStatsList) {
        List<PlayerStatsDto> sortedPlayerStatsDtoList = new ArrayList<>();
        for (PlayerStats playerStats : sortedPlayerStatsList) {
            Player player = playerStats.getPlayer();
            String playerName = player.getPlayerName();
            String playerPosition = player.getPlayerPosition();

            PlayerStatsDto playerStatsDto = PlayerStatsMapper.toDto(playerStats);
            playerStatsDto.setPlayerName(playerName);
            playerStatsDto.setPlayerPosition(playerPosition);

            sortedPlayerStatsDtoList.add(playerStatsDto);
        }

        return sortedPlayerStatsDtoList;
    }

    @Override
    public List<PlayerStatsDto> getSortedPlayerStatsByAssists() {
        List<PlayerStats> sortedPlayerStatsList = playerStatsRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(PlayerStats::getAssists).reversed())
                .toList();

        return getPlayerStatsDtos(sortedPlayerStatsList);
    }

    @Override
    public List<PlayerStatsDto> getSortedPlayerStatsByReds() {
        List<PlayerStats> sortedPlayerStatsList = playerStatsRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(PlayerStats::getReds).reversed())
                .toList();

        return getPlayerStatsDtos(sortedPlayerStatsList);
    }

    @Override
    public List<PlayerStatsDto> getSortedPlayerStatsByYellows() {
        List<PlayerStats> sortedPlayerStatsList = playerStatsRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(PlayerStats::getYellows).reversed())
                .toList();

        return getPlayerStatsDtos(sortedPlayerStatsList);
    }

    @Override
    public void deletePlayerStatsById(Long playerStatsId) {
        if (!playerStatsRepository.existsById(playerStatsId)) {
            throw new EntityNotFoundException("Player statistics not found with ID: " + playerStatsId);
        }
        playerStatsRepository.deleteById(playerStatsId);
    }
}
