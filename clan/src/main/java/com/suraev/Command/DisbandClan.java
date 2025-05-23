package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.Role;
import com.suraev.Entity.Clan;
import java.util.Optional;


public class DisbandClan implements CommandExecutor {

    private final ClanManager clanManager;

    public DisbandClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player) {

           if(clanManager.hasPermission(player, Role.LEADER)) {

            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
            if(optionalClan.isPresent()) {
                clanManager.removeClan(player);
                player.sendMessage("Клан успешно распущен");
            }else{
                player.sendMessage("У вас нет клана");
            }
           }else{
            player.sendMessage("У вас нет прав на распуск клана");
           }
        }
        return false;
    }
}