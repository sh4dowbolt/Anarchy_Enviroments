package com.suraev.Entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Getter
public final class ClanLoader {

    private Map<Long, Clan> clans = new HashMap<>();
    private Gson gson = new Gson();
    private File clansFile;
    private File lastClanIdFile;
    private Long lastClanId;



    public ClanLoader(JavaPlugin plugin) {
        clansFile= new File(plugin.getDataFolder(),"clans.json");
        lastClanIdFile= new File(plugin.getDataFolder(),"lastClanId.txt");
        if(!clansFile.exists()) {
            plugin.getDataFolder().mkdirs();
            try {
                clansFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!lastClanIdFile.exists()) {
            try {
                lastClanIdFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveLastClanId() {
        try(Writer writer = new FileWriter(lastClanIdFile)) {
            writer.write(lastClanId.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadLastClanId() {
        try(Reader reader = new FileReader(lastClanIdFile)) {
           int id = reader.read();
           lastClanId = (long) id;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveClans(Map<Long,Clan> clans) {
        try(Writer writer = new FileWriter(clansFile)) {
            gson.toJson(clans, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void loadClans() {
        try(Reader reader = new FileReader(clansFile)) {
            Type type = new TypeToken<Map<Long, Clan>>(){}.getType();
            Map<Long, Clan> loaded = gson.fromJson(reader,type);
            if (loaded != null) {
                clans = loaded;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
