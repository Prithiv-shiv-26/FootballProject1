package com.footballleague.footballleague.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDetailsDto{
    private Long id;
    private String teamName;
    private String manager;
    private List<PlayerDto> players;

}
