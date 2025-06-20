package com.suraev;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.ClanLoader;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.PluginCommand;

import com.earth2me.essentials.Essentials;
import com.suraev.Command.ClanCommandExecutor;
import com.suraev.Listener.ChatListener;
import com.suraev.TabComplete.ClanTabCompleter;
import org.bukkit.plugin.Plugin;


public class ClanRunner extends JavaPlugin implements Listener {
    private ClanLoader loader;
    private ClanManager clanManager;
    private ClanInviteManager clanInviteManager;
    private Essentials essentials;

    @Override
    public void onEnable() {
        getLogger().info("The clan plugin has been enabled");

        loader = new ClanLoader(this);
        loader.loadClans();

        clanManager= new ClanManager(loader);
        clanInviteManager = new ClanInviteManager(this);

        getServer().getPluginManager().registerEvents(new ChatListener(clanManager), this);

       
        Plugin essentialsPlugin = getServer().getPluginManager().getPlugin("Essentials");
        if(essentialsPlugin != null) {
            getLogger().info("Essentials plugin found");
            essentials = (Essentials) essentialsPlugin;
        } else {
            getLogger().severe("Essentials plugin not found");
        }

        PluginCommand clanCommand = getCommand("clan");
        if(clanCommand != null) {
            clanCommand.setExecutor(new ClanCommandExecutor(clanManager,clanInviteManager,essentials));
        } else {
            getLogger().severe("The clan command is not found");
        }
        getCommand("clan").setTabCompleter(new ClanTabCompleter());



        getLogger().info("Finish loading");
    }

    @Override
    public void onDisable() {
        getLogger().info("The clan plugin has been disabled");
        clanManager.saveClans();
        getLogger().info("The all of the clans have been saved");
    }
}
