package com.footballleague.footballleague.service;

import com.footballleague.footballleague.dto.FixtureDto;

import java.util.List;

public interface FixtureService {
    FixtureDto createFixture(FixtureDto fixtureDto);
    void DeleteFixture(Long fixtureId);
    FixtureDto updateFixture(Long fixtureId, FixtureDto updatedFixtureDto);
    FixtureDto getFixtureById(Long fixtureId);
    List<FixtureDto> getAllFixtures();
}
