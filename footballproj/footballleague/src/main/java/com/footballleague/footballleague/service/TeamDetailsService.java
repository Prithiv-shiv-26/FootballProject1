package com.footballleague.footballleague.service;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.dto.TeamDetailsDto;
import com.footballleague.footballleague.entity.TeamDetails;

import java.util.List;

public interface TeamDetailsService {
    TeamDetailsDto createTeamDetails(TeamDetailsDto teamDetailsDto);
    TeamDetails addPlayersToTeam(Long teamId, List<PlayerDto> playerDtoList); // Modified to accept playerIds
    TeamDetails getTeamDetailsById(Long teamDetailId);
    List<TeamDetailsDto> getAllTeamDetails();
    TeamDetailsDto updateTeamDetails(Long teamDetailId, TeamDetailsDto updatedTeamDetails);
    void deleteTeamDetails(Long teamDetailId);
    TeamDetailsDto getTeamDetailsByName(String teamName);
    void addPlayerToTeamById(Long teamId, Long playerId);
}
