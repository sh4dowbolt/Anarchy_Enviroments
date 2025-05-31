package com.suraev.Entity;

import java.util.ArrayList;
import java.util.List; 
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
@Getter
@Setter
public class Clan {

    private Long id;
    private String title;
    private String description;
    private List<ClanMember> members;
    private Location clanHome;


    public synchronized String getTitle() {
        return title;
    }
    
    public synchronized ClanMember getOwner() {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.LEADER).findFirst().orElse(null);
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public synchronized List<ClanMember> getMembers() {
        return members;
    }

    public synchronized void addMember(ClanMember player) {
        if(members == null) {
            members = new ArrayList<>();
        }
        members.add(player);
    }

    public void removeMember(ClanMember player) {
        members.remove(player);
    }

    public synchronized boolean isPlayerClanLeader(ClanMember checkedClanMember) {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.LEADER)
        .anyMatch(clanMember -> clanMember.equals(checkedClanMember));
    }

    public boolean isPlayerOfficer(ClanMember checkedClanMember) {
        return members.stream().filter(clanMember -> clanMember.getRole() == Role.OFFICER)
        .anyMatch(clanMember -> clanMember.equals(checkedClanMember));
    }

    public synchronized boolean isPlayerInClan(ClanMember clanMember) {
        return members.contains(clanMember);
    }

    public synchronized boolean hasPermission(ClanMember checkedClanMember, Role role) {
        return members.stream().filter(clanmember -> clanmember.equals(checkedClanMember))
        .findFirst().map(clanMember -> clanMember.getRole().equals(role))
        .orElse(false);
    }
}
