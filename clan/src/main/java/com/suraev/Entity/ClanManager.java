package com.suraev.Entity;

import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import com.suraev.Exception.ClanNameAlreadyExistedException;
import com.suraev.Exception.PlayerAlreadyInClanException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.suraev.Entity.DTO.ClanInfo;


public class ClanManager {
    private ClanLoader loader;
    private Map<UUID,Clan> clans;
    private List<ClanInfo> clansInfo;
    private boolean isCacheDirty = true;
    private static AtomicLong lastClanId;

    public ClanManager(ClanLoader loader) {
        this.loader = loader;
        this.clans = new ConcurrentHashMap<>(loader.getClans());
        this.lastClanId = new AtomicLong(loader.getLastClanId());
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
        clan.setId(getLastClanId());
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
        isCacheDirty = true;
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

    public boolean updateClanMemberRole(UUID clanId,Player player,Role role) {
        ClanMember clanMemberByName = new ClanMember(player);
        clans.values().stream().filter(clan -> clan.getId().equals(clanId)).findFirst()
        .ifPresent(clan -> clan.getMembers().stream().filter(clanMemberByName::equals)
        .findFirst().ifPresent(clanMember -> clanMember.setRole(role)));
        return true;
    }

    public boolean removeClan(Player player) {
        ClanMember clanMember = new ClanMember(player);
        Optional<Clan> optionalClan = clans.values().stream().filter(clan -> clan.isPlayerInClan(clanMember)).findFirst();
        if(optionalClan.isPresent()) {
            clans.remove(optionalClan.get().getId());
            return true;
        }
        isCacheDirty = true;
        return false;
    }

    public boolean removeClanByName(String clanName) {
        Optional<Clan> optionalClan =   clans.values().stream()
        .filter(clan -> clan.getTitle().equals(clanName)).findFirst();
        if(optionalClan.isPresent()) {
            clans.remove(optionalClan.get().getId());
            return true;
        }
        isCacheDirty = true;
        return false;
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

    public List<ClanInfo> getClansInfo(int page, int pageSize) {

        if(isCacheDirty || clansInfo == null) {
            clansInfo = clans.values().stream().map(info -> new ClanInfo(info.getId(),info.getTitle()))
            .sorted(Comparator.comparing(ClanInfo::getId,Comparator.naturalOrder()))
            .collect(Collectors.toList());
            isCacheDirty = false;
        }

        return clansInfo.stream()
        .skip((page-1)*pageSize)
        .limit(pageSize)
        .collect(Collectors.toList());
    }

    public int getClansSize() {
        return clans.size();
    }


    public Long getLastClanId() {
        lastClanId.incrementAndGet();
       return lastClanId.get();       
    }
}
