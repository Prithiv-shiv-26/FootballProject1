package com.footballleague.footballleague.mapper;

import com.footballleague.footballleague.dto.TeamDetailsDto;
import com.footballleague.footballleague.entity.TeamDetails;

import java.util.stream.Collectors;

public class TeamDetailsMapper {

    public static TeamDetailsDto mapToTeamDetailsDto(TeamDetails teamDetails){
        TeamDetailsDto teamDetailsDto = new TeamDetailsDto();
                teamDetailsDto.setId(teamDetails.getId());
                teamDetailsDto.setTeamName(teamDetails.getTeamName());
                teamDetailsDto.setManager(teamDetails.getManager());

                teamDetailsDto.setPlayers(teamDetails.getPlayers().stream()
                        .map(PlayerMapper::mapToPlayerDto).collect(Collectors.toList()));
                return teamDetailsDto;
    }

    public static TeamDetails mapToTeamDetails(TeamDetailsDto teamDetailsDto){
        TeamDetails teamDetails = new TeamDetails();
        teamDetails.setId(teamDetailsDto.getId());
        teamDetails.setTeamName(teamDetailsDto.getTeamName());
        teamDetails.setManager(teamDetailsDto.getManager());
        return teamDetails;
    }
}
