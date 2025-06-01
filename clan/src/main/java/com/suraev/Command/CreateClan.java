package com.suraev.Command;

import com.suraev.Entity.ClanManager;
import com.suraev.Exception.ClanNameAlreadyExistedException;
import com.suraev.Exception.PlayerAlreadyInClanException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.suraev.Exception.InvalidClanNameException;


public class CreateClan implements CommandExecutor {

    private ClanManager clanManager;

    public CreateClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            if (strings.length <2) {
                player.sendMessage("§cИспользование: §6/clan create <name>");
                return true;
            }

            String subCommand = strings[0];
            if(!subCommand.equalsIgnoreCase("create")) {
                player.sendMessage("§cНеизвестная подкомада, используй: §6/clan create <name> ");
                return true;
            }

            String titleOfClan= strings[1];
            try {
                clanManager.createClan(player,titleOfClan);
                player.sendMessage("§aКлан успешно создан: §6"+titleOfClan);
            } catch (PlayerAlreadyInClanException  e) {
                player.sendMessage(e.getMessage());
            } catch (ClanNameAlreadyExistedException e) {
                player.sendMessage(e.getMessage());
            } catch (InvalidClanNameException e) {
                player.sendMessage(e.getMessage());
            }

            return true;
        }
        commandSender.sendMessage("§cКоманда только для игроков");
        return true;
    }
}

