package com.suraev.Entity;

import java.util.List;

public class Clan {
    private String title;
    private String description;
    private List<ClanMember> members;
    ClanMember clanLeader;


    public void addMember(ClanMember player) {
        members.add(player);
    }
    public void removeMember(ClanMember player) {
        members.remove(player);
    }
    public void changeClanLeader(ClanMember player) {
        clanLeader=player;
    }

    

}
