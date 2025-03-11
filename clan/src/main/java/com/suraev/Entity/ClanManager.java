package com.suraev.Entity;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClanManager {
    private Map<String, Clan> clans;

    public void createClan(Player player, String name) {

        if(isPlayerInClan(player)) {
            throw new RuntimeException("you are already in clan");
        }

        if(clans.containsKey(name)) {
            throw new RuntimeException("clan existed");
        }


        Clan clan = new Clan();
        ClanMember leader= new ClanMember(player);
        clan.addMember(leader);
        clan.setClanLeader(leader);

        clans.put(name,clan);

    }

    public boolean isPlayerInClan(Player player) {
        return clans.values().stream()
                .flatMap(clan -> clan.getMembers().stream())
                .anyMatch(clanMember -> clanMember.getPlayer().equals(player));

}
}
