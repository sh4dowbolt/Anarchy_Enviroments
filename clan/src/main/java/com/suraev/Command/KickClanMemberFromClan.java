package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.suraev.Entity.ClanManager;
import org.bukkit.entity.Player;

public class KickClanMemberFromClan implements CommandExecutor{

    private final ClanManager clanManager;

    public KickClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 0) {
                player.sendMessage("Неверно указана подкоманда: используй /help для получения списка команд");
                return true;
            }
            if(args.length > 1) {
                player.sendMessage("Указаны лишние аргументы: используй /clan kick");
                return true;
            }
            if(args[0].equalsIgnoreCase("kick")) {
                kickClan(player);
                return true;
            }
        }
        sender.sendMessage("Команда доступна только для игроков");
        return true;
    }

    public boolean kickClan(Player player) {
        return clanManager.kickClan(player);
    }
}