package com.suraev.Command.Admin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager;

public class RenameClan implements CommandExecutor {

    private final ClanManager clanManager;

    public RenameClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }
    
    
}
