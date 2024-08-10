package com.footballleague.footballleague.controller;


import com.footballleague.footballleague.dto.TeamStandingsDto;
import com.footballleague.footballleague.service.TeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/teamstandings")
public class TeamStandingsController {

    @Autowired
    private TeamStandingsService teamStandingsService;

    @PostMapping("/{teamId}")
    public ResponseEntity<TeamStandingsDto> createTeamStandings(@PathVariable("teamId") Long teamId,@RequestBody TeamStandingsDto teamStandingsDto){
        TeamStandingsDto createdTeamStandings = teamStandingsService.createTeamStandings(teamId, teamStandingsDto);
        return new ResponseEntity<>(createdTeamStandings, HttpStatus.CREATED);
    }

    @GetMapping("/{teamStandingsId}")
    public ResponseEntity<TeamStandingsDto> getTeamStandingsByID(@PathVariable Long teamStandingsId){
        TeamStandingsDto teamStandingsDto = teamStandingsService.getTeamStandingsById(teamStandingsId);
        return ResponseEntity.ok(teamStandingsDto);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamStandingsDto> updateTeamStandings(@PathVariable Long teamId, @RequestBody TeamStandingsDto teamStandingsDto){
        TeamStandingsDto updatedTeamStandings = teamStandingsService.updateTeamStandings(teamId, teamStandingsDto);
        return ResponseEntity.ok(updatedTeamStandings);
    }

    @GetMapping
    public ResponseEntity<List<TeamStandingsDto>> getSortedTeamStandings() {
        List<TeamStandingsDto> teamStandings = teamStandingsService.getSortedTeamStandings();
        return ResponseEntity.ok(teamStandings);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeamStandingsByTeamId(@PathVariable Long teamId) {
        teamStandingsService.deleteTeamStandingsByTeamId(teamId);
        return ResponseEntity.noContent().build();
    }
}
