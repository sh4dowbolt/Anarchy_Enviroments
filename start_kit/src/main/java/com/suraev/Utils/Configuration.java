package com.suraev.Utils;

import com.suraev.StartKit;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
    private FileConfiguration config;
    private final StartKit plugin;

    public Configuration(StartKit startKit) {
        this.plugin = startKit;
    }

    public void loadConfig() {
        config =  plugin.getConfig();
        plugin.saveConfig();
        config.options().copyDefaults(true);
    }

    public String getTypeOfKit() {
        return config.getString("settings.type-kit");
    }

    public void setTypeOfKit(String type) {
        config.set("settings.type-kit",type);
    }
}
