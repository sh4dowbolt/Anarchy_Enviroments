package com.suraev;

<<<<<<< HEAD
import com.suraev.Entity.Clan;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClanRunner extends JavaPlugin {

    @Override
    public void onEnable() {
       Map<String, List<Clan>> clans = new HashMap<>();
    }
}
=======
import com.suraev.Command.AcceptInviteToClan;
import com.suraev.Command.CreateClan;
import com.suraev.Command.InviteToClan;
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

        getCommand("сlan create").setExecutor(new CreateClan(clanManager));
        getCommand("clan invite").setExecutor(new InviteToClan(clanInviteManager,clanManager));
        getCommand("clan accept").setExecutor(new AcceptInviteToClan(clanManager, clanInviteManager));

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
>>>>>>> refs/remotes/origin/main
