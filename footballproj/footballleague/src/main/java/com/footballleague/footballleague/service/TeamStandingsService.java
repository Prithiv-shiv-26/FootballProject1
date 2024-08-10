package com.footballleague.footballleague.service;

import com.footballleague.footballleague.dto.TeamStandingsDto;

import java.util.List;

public interface TeamStandingsService {

    TeamStandingsDto createTeamStandings(Long teamId, TeamStandingsDto teamStandingsDto);

    TeamStandingsDto getTeamStandingsById(Long teamStandingsId);

    TeamStandingsDto updateTeamStandings(Long teamId, TeamStandingsDto teamStandingsDto);

    List<TeamStandingsDto> getSortedTeamStandings();

    void deleteTeamStandingsByTeamId(Long teamId);
}
