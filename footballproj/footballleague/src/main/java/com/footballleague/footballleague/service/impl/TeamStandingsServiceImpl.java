package com.footballleague.footballleague.service.impl;

import com.footballleague.footballleague.dto.TeamStandingsDto;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.entity.TeamStandings;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.TeamStandingsMapper;
import com.footballleague.footballleague.repository.TeamDetailsRepository;
import com.footballleague.footballleague.repository.TeamStandingsRepository;
import com.footballleague.footballleague.service.TeamStandingsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamStandingsServiceImpl implements TeamStandingsService {

    private TeamStandingsRepository teamStandingsRepository;


    private TeamDetailsRepository teamDetailsRepository;

    @Override
    public TeamStandingsDto createTeamStandings(Long teamId, TeamStandingsDto teamStandingsDto) {
        // Fetch team details by teamId
        TeamDetails teamDetails = teamDetailsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));

        // Create TeamStandings entity from DTO
        TeamStandings teamStandings = new TeamStandings();
        teamStandings.setTeamDetails(teamDetails);
        teamStandings.setWins(teamStandingsDto.getWins());
        teamStandings.setLosses(teamStandingsDto.getLosses());
        teamStandings.setDraws(teamStandingsDto.getDraws());
        teamStandings.setPoints(calculatePoints(teamStandingsDto));
        teamStandings.setMatchesPlayed(teamStandingsDto.getWins() + teamStandingsDto.getLosses() + teamStandingsDto.getDraws());
        teamStandings.setGoalDifference(teamStandingsDto.getGoalDifference());

        // Save TeamStandings entity
        TeamStandings savedTeamStandings = teamStandingsRepository.save(teamStandings);

        // Map the saved entity back to DTO and return
        return TeamStandingsMapper.mapToTeamStandingsDto(savedTeamStandings);
    }

    @Override
    public TeamStandingsDto getTeamStandingsById(Long teamStandingsId) {
        TeamStandings teamStandings = teamStandingsRepository.findById(teamStandingsId)
                .orElseThrow(() -> new ResourceNotFoundException("Team Standings not found with id: " + teamStandingsId));
        return TeamStandingsMapper.mapToTeamStandingsDto(teamStandings);
    }

    @Override
    public TeamStandingsDto updateTeamStandings(Long teamId, TeamStandingsDto teamStandingsDto) {
        TeamDetails teamDetails = teamDetailsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));

        TeamStandings teamStandings = teamStandingsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team standings not found for team id: " + teamId));

        // Update TeamStandings entity from DTO
        teamStandings.setWins(teamStandingsDto.getWins());
        teamStandings.setLosses(teamStandingsDto.getLosses());
        teamStandings.setDraws(teamStandingsDto.getDraws());
        teamStandings.setPoints(calculatePoints(teamStandingsDto));
        teamStandings.setMatchesPlayed(teamStandingsDto.getWins() + teamStandingsDto.getLosses() + teamStandingsDto.getDraws());
        teamStandings.setGoalDifference(teamStandingsDto.getGoalDifference());

        TeamStandings savedTeamStandings = teamStandingsRepository.save(teamStandings);

        // Map the saved entity back to DTO and return
        return TeamStandingsMapper.mapToTeamStandingsDto(savedTeamStandings);

    }

    @Override
    public List<TeamStandingsDto> getSortedTeamStandings() {
        // Retrieve all team standings from the database
        List<TeamStandings> teamStandingsList = teamStandingsRepository.findAll();

        // Sort team standings based on points, goal difference, and wins
        List<TeamStandings> sortedStandings = teamStandingsList.stream()
                .sorted(Comparator.comparingInt(TeamStandings::getPoints).reversed()
                        .thenComparingInt(TeamStandings::getGoalDifference).reversed()
                        .thenComparingInt(TeamStandings::getWins).reversed())
                .toList();

        // Map sorted standings to DTOs and return
        return sortedStandings.stream()
                .map(teamStandings -> {
                    TeamDetails teamDetails = teamStandings.getTeamDetails(); // Retrieve associated TeamDetails
                    TeamStandingsDto dto = TeamStandingsMapper.mapToTeamStandingsDto(teamStandings);
                    dto.setTeamName(teamDetails.getTeamName()); // Set the team name in the DTO
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteTeamStandingsByTeamId(Long teamId) {
        List<TeamStandings> teamStandingsList = teamStandingsRepository.findByTeamDetails_Id(teamId);
        teamStandingsRepository.deleteAll(teamStandingsList);
    }

    // Method to calculate points based on wins, losses, and draws
    private int calculatePoints(TeamStandingsDto teamStandingsDto) {
        int wins = teamStandingsDto.getWins();
        int draws = teamStandingsDto.getDraws();
        return (wins * 3) + draws;
    }
}
