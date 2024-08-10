package com.footballleague.footballleague.repository;

import com.footballleague.footballleague.entity.Player;
import com.footballleague.footballleague.entity.TeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamDetails(TeamDetails teamDetails);

    List<Player> findByTeamDetailsId(Long teamId);
}
