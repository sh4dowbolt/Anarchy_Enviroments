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
        clans.put(clan.getTitle(), clan);
    }

    public boolean isClanNameExists(String name) {
        return clans.containsKey(name);
    }


    public boolean isPlayerClanLeader(ClanMember clanMember) {
        return clans.values().stream().anyMatch(x->x.isPlayerClanLeader(clanMember));

    }

    public Optional<Clan> findClanByPlayer(ClanMember clanMember) {
        return clans.values().stream()
                .filter(clan -> clan.isPlayerInClan(clanMember)).findFirst();
    }

    public Clan findClanByName(String name) {
        return clans.get(name);
    }
    public boolean isPlayerInClan(ClanMember member) {
        return clans.values().stream()
                .flatMap(clan -> clan.getMembers().stream())
                .anyMatch(clanMember -> clanMember.getUuid().equals(member.getUuid()) && clanMember.getName().equals(member.getName()));
    }

    public void insertPlayerToClan(String name,ClanMember clanMember) {
       clans.get(name).addMember(clanMember);
    }
}
