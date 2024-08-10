package com.footballleague.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamStandingsDto {
    private String TeamName;
    private Long id;
    private int wins;
    private int losses;
    private int draws;
    private int points;
    private int matchesPlayed;
    private int goalDifference;
}
