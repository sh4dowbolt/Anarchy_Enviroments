package com.suraev;


import com.suraev.Listener.OnFirstJoinListener;
import com.suraev.discord.Command.DiscordCommand;
import com.suraev.discord.Sheduler.DiscordInformer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StartKit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {System.out.println("Plugin activated");
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);


        getServer().getPluginManager().registerEvents(new OnFirstJoinListener(),this);
       getLogger().info(this.getName()+"has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName()+"has been disabled");
    }
}
