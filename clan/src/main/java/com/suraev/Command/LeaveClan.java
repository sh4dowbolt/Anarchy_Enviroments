package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager; 
import com.suraev.Entity.Clan;

public class LeaveClan implements CommandExecutor {

    private final ClanManager clanManager;

    public LeaveFromClanCommand(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player player) {
                Clan clan = clanManager.getClanByPlayer(player);
                if(clan == null) {
                    player.sendMessage("Вы не состоите в клане");
                    return true;
                }
                clanManager.removeClanMemberFromClan(player.getName(), player);
            }
        } 
    }
}
