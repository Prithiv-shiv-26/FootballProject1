package com.footballleague.footballleague.dto;

import com.footballleague.footballleague.entity.TeamDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixtureDto {

    private Long id;
    private String homeTeam;
    private String awayTeam;
    private int matchday;
    private String matchDateTime;
}
