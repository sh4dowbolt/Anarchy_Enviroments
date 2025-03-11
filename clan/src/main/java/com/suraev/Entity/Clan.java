package com.suraev.Entity;

import java.util.List;

public class Clan {
    private String title;
    private String description;
    private List<ClanMember> members;
    ClanMember clanLeader;

    public List<ClanMember> getMembers() {
        return members;
    }

    public void addMember(ClanMember player) {
        members.add(player);
    }

    public void removeMember(ClanMember player) {
        members.remove(player);
    }

    public void setClanLeader(ClanMember player) {
        clanLeader=player;
    }

    public boolean isPlayerClanLeader(ClanMember clanMember) {
        return clanMember.equals(clanLeader);
    }

}
