package com.suraev.Entity;

import org.bukkit.entity.Player;

public class ClanManager {

    private ClanLoader loader;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;
    }


    public void createClan(Player player,String name) {

        if() {
            throw new RuntimeException("you are already in the clan");
        }

        if(!loader.isClanNameExists(name)) {
            throw new RuntimeException("clan existed");
        }

        Clan clan = new Clan();
        clan.setTitle(name);
        ClanMember leader= new ClanMember(player, new Rank());
        clan.addMember(leader);
        clan.setClanLeader(leader);

        loader.insertClanWithTitle(clan);

    }

    public boolean isPlayerAlreadyInClan(Player player) {
        return clans.values().stream()
                .flatMap(clan -> clan.getMembers().stream())
                .anyMatch(clanMember -> clanMember.getPlayer().equals(player));
    }




}
