package com.footballleague.footballleague.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="fixtures")
@NoArgsConstructor
@AllArgsConstructor
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id",nullable = false)
    private TeamDetails homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id",nullable = false)
    private TeamDetails awayTeam;

    @Column(name = "matchday",nullable = false,unique = true)
    private int matchday;

    @Column(name = "match_date_time",nullable = false)
    private String matchDateTime;
}
