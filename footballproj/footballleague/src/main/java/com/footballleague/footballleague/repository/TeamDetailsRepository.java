package com.footballleague.footballleague.repository;

import com.footballleague.footballleague.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamDetailsRepository extends JpaRepository<TeamDetails, Long> {
    TeamDetails findByTeamName(String teamName);
}
