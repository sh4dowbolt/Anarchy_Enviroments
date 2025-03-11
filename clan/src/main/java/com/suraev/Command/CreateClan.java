package com.suraev.Command;

import com.suraev.Entity.ClanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateClan implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                ClanManager clanManager = new ClanManager();
                clanManager.createClan(player, strings[0]);
            }
            player.sendMessage("Enter the name of a clan");

            return false;
        }
        return false;
    }
}

