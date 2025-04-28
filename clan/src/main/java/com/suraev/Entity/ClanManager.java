package com.suraev.Entity;

import com.suraev.Entity.DTO.PlayerDTO;
import org.bukkit.entity.Player;

public class ClanManager {

    private ClanLoader loader;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;
    }


    public void createClan(Player player,String name) {
        PlayerDTO playerDTO= new PlayerDTO(player.getName(),player.getUniqueId());

        if(isPlayerAlreadyInClan(playerDTO)) {
            throw new RuntimeException("you are already in the clan");
        }

        if(isClanNameAlreadyExists(name)) {
            throw new RuntimeException("clan existed");
        }

        Clan clan = new Clan();
        clan.setTitle(name);
        ClanMember leader= new ClanMember(player);
        clan.addMember(leader);
        clan.setClanLeader(leader);

        insertClanWithTitle(clan);

    }

    private boolean isClanNameAlreadyExists(String name) {
        return loader.isClanNameExists(name);
    }

    private boolean isPlayerAlreadyInClan(PlayerDTO player) {
        return loader.isPlayerInClan(player);
    }
    private void insertClanWithTitle(Clan clan) {
        loader.insertClan(clan);
    }

}
