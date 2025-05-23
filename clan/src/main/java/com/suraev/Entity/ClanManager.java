package com.suraev.Entity;

import org.bukkit.entity.Player;

import com.suraev.Exception.ClanNameAlreadyExistedException;
import com.suraev.Exception.PlayerAlreadyInClanException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;

public class ClanManager {

    private ClanLoader loader;
    private Map<UUID,Clan> clans;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;

        this.clans = new ConcurrentHashMap<>(loader.getClans());
    }   
    

    public void createClan(Player player,String name) throws PlayerAlreadyInClanException, ClanNameAlreadyExistedException {

       if(validateClanName(name)) {

        ClanMember playerDTO= new ClanMember(player);

        if(isPlayerAlreadyInClan(playerDTO)) {
            throw new PlayerAlreadyInClanException("Вы уже находитесь в клане");
        }


        if(isClanNameAlreadyExists(name)) {
            throw new ClanNameAlreadyExistedException("Клан с таким названием уже существует"); 
        }

        Clan clan = new Clan();
        clan.setId(generateUniqueId());
        clan.setTitle(name);
        ClanMember leader= new ClanMember(player);
        leader.setRole(Role.LEADER);
        clan.addMember(leader);

        insertClanWithTitle(clan);
    } else {
            player.sendMessage("Название клана должно быть от 3 до 10 символов");
        }
    }

    private boolean validateClanName(String name) {
        return name.length() >= 3 && name.length() <= 10;
    }

    private boolean isClanNameAlreadyExists(String name) {
        return clans.values().stream().anyMatch(clan -> clan.getTitle().equals(name));
    }

    public boolean isPlayerAlreadyInClan(ClanMember player) {
        return clans.values().stream().anyMatch(clan -> clan.isPlayerInClan(player));
    }

    private void insertClanWithTitle(Clan clan) {
        clans.put(clan.getId(), clan);
    }

    public boolean isPlayerClanLeader(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return clans.values().stream().anyMatch(clan -> clan.isPlayerClanLeader(clanMember));
    }

    public boolean isPlayerOfficer(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return clans.values().stream().anyMatch(clan -> clan.isPlayerOfficer(clanMember));
    }

    public Optional<Clan> getClanByPlayer(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return clans.values().stream().filter(clan -> clan.isPlayerInClan(clanMember)).findFirst();
    }

    public Optional<Clan> getClanByName(String name) {
        return clans.values().stream().filter(clan -> clan.getTitle().equals(name)).findFirst();
    }

    public boolean addClanMemberToClan(String name,Player player) {
        ClanMember clanMember = new ClanMember(player);
        clans.values().stream().filter(clan -> clan.getTitle().equals(name)).findFirst().ifPresent(clan -> clan.addMember(clanMember));
        return true;
    }
    
    public boolean removeClanMemberFromClan(String clanName,Player player) {
        ClanMember clanMember = new ClanMember(player);
        clans.values().stream().filter(clan -> clan.getTitle().equals(clanName)).findFirst().ifPresent(clan -> clan.removeMember(clanMember));
        return true;
    }

    public boolean removeClan(Player player) {
        ClanMember clanMember = new ClanMember(player);
        Optional<Clan> optionalClan = clans.values().stream().filter(clan -> clan.isPlayerInClan(clanMember)).findFirst();
        if(optionalClan.isPresent()) {
            clans.remove(optionalClan.get().getId());
            return true;
        }
        return false;
    }
    public UUID generateUniqueId() {
        return UUID.randomUUID();
    }

    public void saveClans() {
        loader.saveClans(clans);
    }

    public boolean hasPermission(Player player, Role role) {
        ClanMember clanMember = new ClanMember(player);
        return clans.values().stream().filter(clan -> clan.isPlayerInClan(clanMember))
        .findFirst().map(clan -> clan.hasPermission(clanMember, role))
        .orElse(false);
    }
}
