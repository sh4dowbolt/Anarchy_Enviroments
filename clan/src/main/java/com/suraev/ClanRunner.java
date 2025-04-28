package com.suraev;

import com.suraev.Command.CreateClan;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.ClanLoader;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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

        getCommand("create-clan").setExecutor(new CreateClan(clanManager));

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
