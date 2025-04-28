package com.suraev.Command;

import com.suraev.Entity.ClanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CreateClan implements CommandExecutor {

    private ClanManager clanManager;

    public CreateClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            if (strings.length == 1) {

                clanManager.createClan(player,strings[0]);
                player.sendMessage("The clan has been created");
            }
            player.sendMessage("Enter the name of a clan");

            return false;
        }
        return false;
    }
}

