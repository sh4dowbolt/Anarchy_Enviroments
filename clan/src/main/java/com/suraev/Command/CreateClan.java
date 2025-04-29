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

            if (strings.length <2) {
                player.sendMessage("Использование: /clan create <name>");
                return true;
            }

            String subCommand = strings[0];
            if(!subCommand.equalsIgnoreCase("create")) {
                player.sendMessage("Неизвестная подкомада, используй: /clan create <name> ");
                return true;
            }

            String titleOfClan= strings[1];
            clanManager.createClan(player,titleOfClan);

            return true;
        }
        commandSender.sendMessage("Команда только для игроков");
        return true;
    }
}

