package com.footballleague.footballleague.controller;

import com.footballleague.footballleague.dto.PlayerStatsDto;
import com.footballleague.footballleague.service.PlayerStatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/playerstats")
public class PlayerStatsController {
    private PlayerStatsService playerStatsService;

    @PostMapping("/create/{playerId}")
    public ResponseEntity<PlayerStatsDto> createPlayerStats(@PathVariable("playerId") Long playerId, @RequestBody PlayerStatsDto playerStatsDto) {
        PlayerStatsDto createdPlayerStats = playerStatsService.createPlayerStats(playerId, playerStatsDto);
        return new ResponseEntity<>(createdPlayerStats, HttpStatus.CREATED);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerStatsDto> getPlayerStats(@PathVariable("playerId") Long playerId) {
        PlayerStatsDto playerStatsDto = playerStatsService.getPlayerStats(playerId);
        return new ResponseEntity<>(playerStatsDto, HttpStatus.OK);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerStatsDto> updatePlayerStats(@PathVariable("playerId") Long playerId, @RequestBody PlayerStatsDto playerStatsDto) {
        PlayerStatsDto updatedPlayerStats = playerStatsService.updatePlayerStats(playerId, playerStatsDto);
        return new ResponseEntity<>(updatedPlayerStats, HttpStatus.OK);
    }

    @DeleteMapping("/{playerStatsId}")
    public ResponseEntity<Void> deletePlayerStatsById(@PathVariable("playerStatsId") Long playerStatsId) {
        playerStatsService.deletePlayerStatsById(playerStatsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerStatsDto>> getAllPlayerStats() {
        List<PlayerStatsDto> allPlayerStats = playerStatsService.getAllPlayerStats();
        return new ResponseEntity<>(allPlayerStats, HttpStatus.OK);
    }

    @GetMapping("/sorted/goals")
    public ResponseEntity<List<PlayerStatsDto>> getSortedPlayerStatsByGoals() {
        List<PlayerStatsDto> sortedPlayerStats = playerStatsService.getSortedPlayerStatsByGoals();
        return new ResponseEntity<>(sortedPlayerStats, HttpStatus.OK);
    }

    @GetMapping("/sorted/assists")
    public ResponseEntity<List<PlayerStatsDto>> getSortedPlayerStatsByAssists() {
        List<PlayerStatsDto> sortedPlayerStats = playerStatsService.getSortedPlayerStatsByAssists();
        return new ResponseEntity<>(sortedPlayerStats, HttpStatus.OK);
    }

    @GetMapping("/sorted/reds")
    public ResponseEntity<List<PlayerStatsDto>> getSortedPlayerStatsByReds() {
        List<PlayerStatsDto> sortedPlayerStats = playerStatsService.getSortedPlayerStatsByReds();
        return new ResponseEntity<>(sortedPlayerStats, HttpStatus.OK);
    }

    @GetMapping("/sorted/yellows")
    public ResponseEntity<List<PlayerStatsDto>> getSortedPlayerStatsByYellows() {
        List<PlayerStatsDto> sortedPlayerStats = playerStatsService.getSortedPlayerStatsByYellows();
        return new ResponseEntity<>(sortedPlayerStats, HttpStatus.OK);
    }

}
