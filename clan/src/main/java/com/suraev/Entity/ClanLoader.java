package com.suraev.Entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ClanLoader {

    private Map<String, Clan> clans = new HashMap<>();
    private Gson gson = new Gson();
    private File clansFile;



    public ClanLoader(JavaPlugin plugin) {
        clansFile= new File(plugin.getDataFolder(),"clans.json");
        if(!clansFile.exists()) {
            plugin.getDataFolder().mkdirs();
            try {
                clansFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveClans() {
        try(Writer writer = new FileWriter(clansFile)) {
            gson.toJson(clans, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadClans() {
        try(Reader reader = new FileReader(clansFile)) {
            Type type = new TypeToken<Map<String, Clan>>(){}.getType();
            Map<String, Clan> loaded = gson.fromJson(reader,type);
            if (loaded != null) {
                clans = loaded;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertClan(Clan clan) {
        clans.put(clan.getId(), clan);
    }

    public boolean isClanNameExists(String name) {
        return clans.values().stream().anyMatch(clan -> clan.getTitle().equals(name));
    }


    public boolean isPlayerClanLeader(ClanMember clanMember) {
        return clans.values().stream().anyMatch(clan -> clan.isPlayerClanLeader(clanMember));
    }

    public boolean isPlayerOfficer(ClanMember clanMember) {
        return clans.values().stream().anyMatch(clan -> clan.isPlayerOfficer(clanMember));
    }

    public Optional<Clan> findClanByPlayer(ClanMember clanMember) {
        return clans.values().stream()
                .filter(clan -> clan.isPlayerInClan(clanMember)).findFirst();
    }

    public Optional<Clan> findClanByName(String name) {
        return clans.values().stream().filter(clan -> clan.getTitle().equals(name)).findFirst();
    }
    public boolean isPlayerInClan(ClanMember member) {
        return clans.values().stream()
                .flatMap(clan -> clan.getMembers().stream())
                .anyMatch(clanMember -> clanMember.getUuid().equals(member.getUuid()) && clanMember.getName().equals(member.getName()));
    }

    public void insertPlayerToClan(String name,ClanMember clanMember) {
        clans.values().stream().filter(clan -> clan.getTitle().equals(name)).findFirst()
        .ifPresent(clan -> clan.addMember(clanMember));
    }

    public void removePlayerFromClan(String name,ClanMember clanMember) {
        clans.values().stream().filter(clan -> clan.getTitle().equals(name)).findFirst()
        .ifPresent(clan -> clan.removeMember(clanMember));
    }

    public void removeClan(String clanName) {
       clans.values().stream().filter(clan -> clan.getTitle().equals(clanName))
       .findFirst().ifPresent(clan -> clans.remove(clan.getId()));
    }
}
