package com.suraev.Entity;

import org.bukkit.entity.Player;

import com.suraev.Exception.ClanNameAlreadyExistedException;
import com.suraev.Exception.PlayerAlreadyInClanException;
import java.util.Optional;
import java.util.UUID;

public class ClanManager {

    private ClanLoader loader;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;
    }   


    public void createClan(Player player,String name) throws PlayerAlreadyInClanException, ClanNameAlreadyExistedException {

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

    public boolean isPlayerOfficer(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return loader.isPlayerOfficer(clanMember);
    }

    public Clan getClanByPlayer(Player player) {
        ClanMember clanMember = new ClanMember(player);
        Optional<Clan> clanByPlayer = loader.findClanByPlayer(clanMember);

        return clanByPlayer.orElse(null);

    }

    public Clan getClanByName(String name) {
        return loader.findClanByName(name);
    }

    public boolean addClanMemberToClan(String name,Player player) {
        ClanMember clanMember = new ClanMember(player);
        loader.insertPlayerToClan(name, clanMember);
        return true;
    }
    
    public boolean removeClanMemberFromClan(String clanName,Player player) {
        ClanMember clanMember = new ClanMember(player);
        loader.removePlayerFromClan(clanName, clanMember);
        return true;
    }

    public boolean removeClan(Player player) {
        ClanMember clanMember = new ClanMember(player);
        Optional<Clan> clan = loader.findClanByPlayer(clanMember);
        if(clan.isPresent()) {
            loader.removeClan(clan.get().getTitle());
            return true;
        }
        return false;
    }

    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
