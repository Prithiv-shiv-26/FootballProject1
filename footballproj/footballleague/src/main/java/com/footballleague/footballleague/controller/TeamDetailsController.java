package com.footballleague.footballleague.controller;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.dto.TeamDetailsDto;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.TeamDetailsMapper;
import com.footballleague.footballleague.service.TeamDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/teamdetails")
public class TeamDetailsController {

    private final TeamDetailsService teamDetailsService;

    @PostMapping("/create")
    public ResponseEntity<TeamDetailsDto> createTeamDetails(@RequestBody TeamDetailsDto teamDetailsDto) {
        TeamDetailsDto createdTeamDetails = teamDetailsService.createTeamDetails(teamDetailsDto);
        return new ResponseEntity<>(createdTeamDetails, HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/addplayers")
    public ResponseEntity<TeamDetailsDto> addPlayersToTeam(@PathVariable("teamId") Long teamId, @RequestBody List<PlayerDto> playerDtoList) {
        TeamDetails updatedTeamDetails = teamDetailsService.addPlayersToTeam(teamId, playerDtoList);//line 30
        TeamDetailsDto updatedTeamDetailsDto = TeamDetailsMapper.mapToTeamDetailsDto(updatedTeamDetails);
        return new ResponseEntity<>(updatedTeamDetailsDto, HttpStatus.OK);
    }

    @GetMapping("/{teamDetailId}")
    public ResponseEntity<TeamDetails> getTeamDetailsById(@PathVariable("teamDetailId") Long teamDetailId) {
        TeamDetails teamDetailsDto = teamDetailsService.getTeamDetailsById(teamDetailId);
        return ResponseEntity.ok(teamDetailsDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeamDetailsDto>> getAllTeamDetails() {
        List<TeamDetailsDto> teamDetailsDtoList = teamDetailsService.getAllTeamDetails();
        return ResponseEntity.ok(teamDetailsDtoList);
    }

    @PutMapping("/{teamDetailId}/update")
    public ResponseEntity<TeamDetailsDto> updateTeamDetails(@PathVariable("teamDetailId") Long teamDetailId, @RequestBody TeamDetailsDto updatedTeamDetails) {
        TeamDetailsDto teamDetailsDto = teamDetailsService.updateTeamDetails(teamDetailId, updatedTeamDetails);
        return ResponseEntity.ok(teamDetailsDto);
    }

    @DeleteMapping("/{teamDetailId}/delete")
    public ResponseEntity<Void> deleteTeamDetails(@PathVariable("teamDetailId") Long teamDetailId) {
        teamDetailsService.deleteTeamDetails(teamDetailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byname/{teamName}")
    public ResponseEntity<TeamDetailsDto> getTeamDetailsByName(@PathVariable("teamName") String teamName) {
        TeamDetailsDto teamDetailsDto = teamDetailsService.getTeamDetailsByName(teamName);
        return ResponseEntity.ok(teamDetailsDto);
    }

    @PostMapping("/{teamId}/addPlayer/{playerId}")
    public ResponseEntity<Void> addPlayerToTeamById(@PathVariable("teamId") Long teamId,@PathVariable("playerId") Long playerId){
        try {
            teamDetailsService.addPlayerToTeamById(teamId, playerId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
