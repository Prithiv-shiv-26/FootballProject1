package com.footballleague.footballleague.mapper;

import com.footballleague.footballleague.dto.PlayerDto;
import com.footballleague.footballleague.entity.Player;

public class PlayerMapper {

    public static PlayerDto mapToPlayerDto(Player player){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setPlayerName(player.getPlayerName());
        playerDto.setPlayerPosition(player.getPlayerPosition());
        return playerDto;
    }

    public static Player mapToPlayer(PlayerDto playerDto){
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setPlayerName(playerDto.getPlayerName());
        player.setPlayerPosition(playerDto.getPlayerPosition());
        return player;
    }
}
