package com.footballleague.footballleague.mapper;

import com.footballleague.footballleague.dto.TeamStandingsDto;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.entity.TeamStandings;

public class TeamStandingsMapper {
    public static TeamStandingsDto  mapToTeamStandingsDto(TeamStandings teamStandings){
        TeamStandingsDto dto = new TeamStandingsDto();
        dto.setId(teamStandings.getId());
        dto.setWins(teamStandings.getWins());
        dto.setDraws(teamStandings.getDraws());
        dto.setLosses(teamStandings.getLosses());
        dto.setPoints(teamStandings.getPoints());
        dto.setGoalDifference(teamStandings.getGoalDifference());
        dto.setMatchesPlayed(teamStandings.getMatchesPlayed());
        dto.setTeamName(teamStandings.getTeamDetails().getTeamName());
        return dto;
    }

    public static TeamStandings mapToTeamStandings(TeamStandingsDto dto){
        TeamStandings teamStandings = new TeamStandings();
        teamStandings.setId(dto.getId());
        teamStandings.setWins(dto.getWins());
        teamStandings.setDraws(dto.getDraws());
        teamStandings.setLosses(dto.getLosses());
        teamStandings.setPoints(dto.getPoints());
        teamStandings.setGoalDifference(dto.getGoalDifference());
        teamStandings.setMatchesPlayed(dto.getMatchesPlayed());
        return teamStandings;
    }
}
