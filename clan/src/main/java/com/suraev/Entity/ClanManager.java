package com.suraev.Entity;

import org.bukkit.entity.Player;

import java.util.Optional;

public class ClanManager {

    private ClanLoader loader;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;
    }


    public void createClan(Player player,String name) {
        ClanMember playerDTO= new ClanMember(player);

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

    public boolean isPlayerAlreadyInClan(ClanMember player) {
        return loader.isPlayerInClan(player);
    }
    private void insertClanWithTitle(Clan clan) {
        loader.insertClan(clan);
    }

    public boolean isPlayerClanLeader(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return loader.isPlayerClanLeader(clanMember);
    }

    public Clan getClanByPlayer(Player player) {
        ClanMember clanMember = new ClanMember(player);
        Optional<Clan> clanByPlayer = loader.findClanByPlayer(clanMember);

        return clanByPlayer.orElse(null);

    }

}
