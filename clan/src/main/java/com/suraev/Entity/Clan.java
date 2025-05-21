package com.suraev.Entity;

import java.util.ArrayList;
import java.util.List; 
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Clan {
    
    private Long id;
    private String title;
    private String description;
    private List<ClanMember> members;


    public String getTitle() {
        return title;
    }
    
    public ClanMember getOwner() {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.LEADER).findFirst().orElse(null);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ClanMember> getMembers() {
        return members;
    }

    public void addMember(ClanMember player) {
        if(members == null) {
            members = new ArrayList<>();
        }
        members.add(player);
    }

    public void removeMember(ClanMember player) {
        members.remove(player);
    }

    public boolean isPlayerClanLeader(ClanMember checkedClanMember) {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.LEADER)
        .anyMatch(clanMember -> clanMember.equals(checkedClanMember));
    }

    public boolean isPlayerOfficer(ClanMember checkedClanMember) {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.OFFICER)
        .anyMatch(clanMember -> clanMember.equals(checkedClanMember));
    }

    public boolean isPlayerInClan(ClanMember clanMember) {
        return members.contains(clanMember);
    }
}
