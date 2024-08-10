package com.footballleague.footballleague.repository;

import com.footballleague.footballleague.entity.TeamStandings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamStandingsRepository extends JpaRepository<TeamStandings, Long> {
    List<TeamStandings> findByTeamDetails_Id(Long teamId);
}
