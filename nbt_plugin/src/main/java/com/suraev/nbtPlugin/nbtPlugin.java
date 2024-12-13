package com.suraev.nbtPlugin;

import com.suraev.nbtPlugin.Command.NbtClearCommand;
import com.suraev.nbtPlugin.Command.NbtCounterCommand;
import com.suraev.nbtPlugin.Command.NbtEnchantment;
import com.suraev.nbtPlugin.Command.NbtTitleCommand;
import com.suraev.nbtPlugin.Listeners.KillCounter;
import com.suraev.nbtPlugin.Listeners.PlayerEmptySpace;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class nbtPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {System.out.println("Plugin activated");

       getCommand("nbt-title").setExecutor(new NbtTitleCommand());
       getCommand("nbt-counter").setExecutor(new NbtCounterCommand());
       getCommand("nbt-ench").setExecutor(new NbtEnchantment());
       getCommand("nbt-clear").setExecutor(new NbtClearCommand());

       getServer().getPluginManager().registerEvents(new PlayerEmptySpace(),this);
       getServer().getPluginManager().registerEvents(new KillCounter(),this);

       getLogger().info(this.getName()+"has been enabled");
    }


    @Override
    public void onDisable() {
        getLogger().info(this.getName()+"has been disabled");
    }
}
