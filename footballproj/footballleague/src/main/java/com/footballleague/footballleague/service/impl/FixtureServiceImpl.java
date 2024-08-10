package com.footballleague.footballleague.service.impl;

import com.footballleague.footballleague.dto.FixtureDto;
import com.footballleague.footballleague.entity.Fixture;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.FixtureMapper;
import com.footballleague.footballleague.repository.FixtureRepository;
import com.footballleague.footballleague.repository.TeamDetailsRepository;
import com.footballleague.footballleague.service.FixtureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FixtureServiceImpl implements FixtureService {
    private FixtureRepository fixtureRepository;
    private TeamDetailsRepository teamDetailsRepository;
    @Override
    public FixtureDto createFixture(FixtureDto fixtureDto) {
        try {
            Fixture fixture = FixtureMapper.mapToFixture(fixtureDto, teamDetailsRepository);
            Fixture savedFixture = fixtureRepository.save(fixture);
            return FixtureMapper.mapToFixtureDto(savedFixture);
        } catch (Exception e){
            throw ResourceNotFoundException.fixtureCreationFailed("Error creating fixture: "+e.getMessage());
        }
    }

    @Override
    public void DeleteFixture(Long fixtureId) {
        if (!fixtureRepository.existsById(fixtureId)) {
            throw new ResourceNotFoundException("Fixture not found with ID: " + fixtureId);
        }
        fixtureRepository.deleteById(fixtureId);
    }

    @Override
    public FixtureDto updateFixture(Long fixtureId, FixtureDto updatedFixtureDto) {
        Fixture existingFixture = fixtureRepository.findById(fixtureId)
                .orElseThrow(() -> new ResourceNotFoundException("Fixture not found with id: " + fixtureId));
        Fixture updatedFixture = FixtureMapper.mapToFixture(updatedFixtureDto, teamDetailsRepository);
        updatedFixture.setId(existingFixture.getId()); // Ensure the ID is not changed
        Fixture savedFixture = fixtureRepository.save(updatedFixture);
        return FixtureMapper.mapToFixtureDto(savedFixture);
    }

    @Override
    public FixtureDto getFixtureById(Long fixtureId) {
        Fixture fixture = fixtureRepository.findById(fixtureId)
                .orElseThrow(() -> new ResourceNotFoundException("Fixture not found with id: " + fixtureId));
        return FixtureMapper.mapToFixtureDto(fixture);
    }

    @Override
    public List<FixtureDto> getAllFixtures() {
        List<Fixture> fixtures = fixtureRepository.findAll();
        return fixtures.stream()
                .map(FixtureMapper::mapToFixtureDto)
                .collect(Collectors.toList());
    }
}
