package com.footballleague.footballleague.controller;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.service.PlayerService;
import com.footballleague.footballleague.service.TeamDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;
    private final TeamDetailsService teamDetailsService;

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("playerId") Long playerId) {
        try {
            PlayerDto playerDto = playerService.getPlayerById(playerId);
            return ResponseEntity.ok(playerDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerDto> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable("playerId") Long playerId, @RequestBody PlayerDto updatedPlayer) {
        try {
            PlayerDto playerDto = playerService.updatePlayer(playerId, updatedPlayer);
            return ResponseEntity.ok(playerDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("playerId") Long playerId) {
        try {
            playerService.deletePlayer(playerId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeam(@PathVariable("teamId") Long teamId) {
        try {
            TeamDetails teamDetails = teamDetailsService.getTeamDetailsById(teamId);
            List<PlayerDto> players = playerService.getPlayersByTeam(teamDetails);
            return ResponseEntity.ok(players);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/team/{teamId}")
    public ResponseEntity<Void> addPlayersToTeam(@PathVariable("teamId") Long teamId, @RequestBody List<PlayerDto> playerDtoList) {
        try {
            TeamDetails teamDetails = teamDetailsService.getTeamDetailsById(teamId);
            playerService.addPlayersToTeam(teamDetails, playerDtoList);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
