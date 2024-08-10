package com.footballleague.footballleague.service.impl;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.dto.TeamDetailsDto;
import com.footballleague.footballleague.entity.Player;
import com.footballleague.footballleague.entity.TeamDetails;
import com.footballleague.footballleague.exception.ResourceNotFoundException;
import com.footballleague.footballleague.mapper.PlayerMapper;
import com.footballleague.footballleague.mapper.TeamDetailsMapper;
import com.footballleague.footballleague.repository.PlayerRepository;
import com.footballleague.footballleague.repository.TeamDetailsRepository;
import com.footballleague.footballleague.service.PlayerService;
import com.footballleague.footballleague.service.TeamDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TeamDetailsServiceImpl implements TeamDetailsService {
    private final TeamDetailsRepository teamDetailsRepository;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    @Override
    public TeamDetailsDto createTeamDetails(TeamDetailsDto teamDetailsDto) {
        TeamDetails teamDetails = TeamDetailsMapper.mapToTeamDetails(teamDetailsDto);
        return TeamDetailsMapper.mapToTeamDetailsDto(teamDetailsRepository.save(teamDetails));
    }

    @Override
    public TeamDetails addPlayersToTeam(Long teamId, List<PlayerDto> playerDtoList) {
        TeamDetails team = teamDetailsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));

        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : playerDtoList) {
            Player player = PlayerMapper.mapToPlayer(playerDto);
            player.setTeamDetails(team); // Set the team for each player
            players.add(player);
        }

        team.getPlayers().addAll(players); // Ensure bidirectional relationship is managed
        team = teamDetailsRepository.save(team);//line 45
        return team;
    }


    @Override
    public TeamDetails getTeamDetailsById(Long teamDetailId) {
        return teamDetailsRepository.findById(teamDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Team details not found with id: " + teamDetailId));
    }

    @Override
    public List<TeamDetailsDto> getAllTeamDetails() {
        List<TeamDetails> teamDetailsList = teamDetailsRepository.findAll();
        return teamDetailsList.stream()
                .map(TeamDetailsMapper::mapToTeamDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDetailsDto updateTeamDetails(Long teamDetailId, TeamDetailsDto updatedTeamDetails) {
        TeamDetails teamDetails = teamDetailsRepository.findById(teamDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Team details not found with id: " + teamDetailId));
        teamDetails.setTeamName(updatedTeamDetails.getTeamName());
        teamDetails.setManager(updatedTeamDetails.getManager());
        return TeamDetailsMapper.mapToTeamDetailsDto(teamDetailsRepository.save(teamDetails));
    }

    @Override
    public void deleteTeamDetails(Long teamDetailId) {
        TeamDetails teamDetails = teamDetailsRepository.findById(teamDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Team details not found with id: " + teamDetailId));
        teamDetailsRepository.delete(teamDetails);
    }

    @Override
    public TeamDetailsDto getTeamDetailsByName(String teamName) {
        TeamDetails teamDetails = teamDetailsRepository.findByTeamName(teamName);
        if (teamDetails == null) {
            throw new ResourceNotFoundException("Team details not found with name: " + teamName);
        }
        return TeamDetailsMapper.mapToTeamDetailsDto(teamDetails);
    }

    @Override
    public void addPlayerToTeamById(Long teamId, Long playerId) {
        TeamDetails team = teamDetailsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + playerId));

        player.setTeamDetails(team);
        playerRepository.save(player);
    }

}

