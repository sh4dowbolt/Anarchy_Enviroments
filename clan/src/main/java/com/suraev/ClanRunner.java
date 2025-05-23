package com.suraev;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.ClanLoader;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.PluginCommand;
import com.suraev.Command.ClanCommandExecutor;

public class ClanRunner extends JavaPlugin implements Listener {
    private ClanLoader loader;
    private ClanManager clanManager;
    private ClanInviteManager clanInviteManager;

    @Override
    public void onEnable() {
        getLogger().info("The clan plugin has been enabled");

        loader = new ClanLoader(this);

        clanManager= new ClanManager(loader);
        clanInviteManager = new ClanInviteManager(this);

        PluginCommand clanCommand = getCommand("clan");
        if(clanCommand != null) {
            clanCommand.setExecutor(new ClanCommandExecutor(clanManager,clanInviteManager));
        } else {
            getLogger().severe("The clan command is not found");
        }
        
        loader.loadClans();
        getLogger().info("Finish loading");
    }

    @Override
    public void onDisable() {
        getLogger().info("The clan plugin has been disabled");
        loader.saveClans();
        getLogger().info("The all of the clans have been saved");
    }
}
