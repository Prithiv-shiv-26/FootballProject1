package com.footballleague.footballleague.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team_standings")
public class TeamStandings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id",nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TeamDetails teamDetails;
    @Column(name = "wins")
    private int wins;
    @Column(name = "losses")
    private int losses;
    @Column(name = "matches_played")
    private int matchesPlayed;
    @Column(name = "goal_difference")
    private int goalDifference;
    @Column(name = "points")
    private int points;
    @Column(name = "draws")
    private int draws;
}
