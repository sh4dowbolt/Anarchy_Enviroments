package com.suraev.discord;


import com.suraev.discord.Command.DiscordCommand;
import com.suraev.discord.Sheduler.DiscordInformer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class discordInform extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {System.out.println("Plugin activated");
       getCommand("discord").setExecutor(new DiscordCommand());
       getCommand("vote").setExecutor(new DiscordCommand());
       Bukkit.getScheduler().runTaskTimerAsynchronously(this, new DiscordInformer(),0,12000);
       getLogger().info(this.getName()+"has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName()+"has been disabled");
    }
}
