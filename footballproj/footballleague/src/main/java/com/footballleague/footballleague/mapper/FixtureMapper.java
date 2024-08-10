package com.footballleague.footballleague.mapper;

import com.footballleague.footballleague.dto.FixtureDto;
import com.footballleague.footballleague.entity.Fixture;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.repository.TeamDetailsRepository;


public class FixtureMapper {
    public static FixtureDto mapToFixtureDto(Fixture fixture){
        FixtureDto fixtureDto = new FixtureDto();
        fixtureDto.setId(fixture.getId());
        fixtureDto.setHomeTeam(fixture.getHomeTeam().getTeamName());
        fixtureDto.setAwayTeam(fixture.getAwayTeam().getTeamName());
        fixtureDto.setMatchday(fixture.getMatchday());
        fixtureDto.setMatchDateTime(fixture.getMatchDateTime());
        return fixtureDto;
    }

    public static Fixture mapToFixture(FixtureDto fixtureDto, TeamDetailsRepository teamDetailsRepository){
        Fixture fixture = new Fixture();
        fixture.setId(fixtureDto.getId());
        TeamDetails homeTeam = teamDetailsRepository.findByTeamName(fixtureDto.getHomeTeam());
        TeamDetails awayTeam = teamDetailsRepository.findByTeamName(fixtureDto.getAwayTeam());
        fixture.setHomeTeam(homeTeam);
        fixture.setAwayTeam(awayTeam);
        fixture.setMatchday(fixtureDto.getMatchday());
        fixture.setMatchDateTime(fixtureDto.getMatchDateTime());
        return fixture;
    }
}
