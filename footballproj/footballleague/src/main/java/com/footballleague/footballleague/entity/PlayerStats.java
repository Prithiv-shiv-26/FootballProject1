package com.footballleague.footballleague.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_stats")
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id",nullable = false)
    private Player player;
    @Column(name = "goals")
    private int goals;
    @Column(name = "assists")
    private int assists;
    @Column(name = "yellows")
    private int yellows;
    @Column(name = "reds")
    private int reds;
}
