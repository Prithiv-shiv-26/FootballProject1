package com.footballleague.footballleague.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teamdetails")
public class TeamDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Team_Name", unique = true,nullable = false)
    private String teamName;
    @Column(name = "manager",nullable = false)
    private String manager;
    @JsonManagedReference
    @OneToMany(mappedBy = "teamDetails", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Player> players = new ArrayList<>();
    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.REMOVE)
    private List<Fixture> homeFixtures = new ArrayList<>();
    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.REMOVE)
    private List<Fixture> awayFixtures = new ArrayList<>();
    @OneToOne(mappedBy = "teamDetails", cascade = CascadeType.REMOVE)
    private TeamStandings teamStandings;
}
