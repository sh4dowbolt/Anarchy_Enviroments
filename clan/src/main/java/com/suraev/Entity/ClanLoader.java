package com.suraev.Entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import java.util.UUID;

@Getter
public final class ClanLoader {

    private Map<UUID, Clan> clans = new HashMap<>();
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
            Type type = new TypeToken<Map<UUID, Clan>>(){}.getType();
            Map<UUID, Clan> loaded = gson.fromJson(reader,type);
            if (loaded != null) {
                clans = loaded;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
