package com.footballleague.footballleague.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsDto {
    private Long id;
    private Long playerId;
    private String playerName;
    private String playerPosition;
    private int goals;
    private int assists;
    private int yellows;
    private int reds;
}
