package com.suraev;


import com.suraev.Command.SetTypeOfKit;
import com.suraev.Listener.OnFirstJoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StartKit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {System.out.println("Plugin activated");
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);

        
        getCommand("set_type_kit").setExecutor(new SetTypeOfKit(config));

        getLogger().info(this.getName()+"has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName()+"has been disabled");
    }
}
