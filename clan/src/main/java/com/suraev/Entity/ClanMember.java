package com.suraev.Entity;

import org.bukkit.entity.Player;

import java.util.Objects;


public class ClanMember {

    private Player player;
    //private Rank rank;

    public ClanMember(Player player, Rank rank) {
        this.player = player;
        //this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClanMember that = (ClanMember) object;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(player);
    }
}
