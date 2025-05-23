package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.suraev.Entity.ClanManager;
import org.bukkit.entity.Player;

public class RemoveClan implements CommandExecutor {

    private final ClanManager clanManager;

    public RemoveClan(ClanManager clanManager) {
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
                player.sendMessage("Указаны лишние аргументы: используй /clan remove");
                return true;
            }
            if(args[0].equalsIgnoreCase("remove")) {
                removeClan(player);
                player.sendMessage("Клан успешно удален");
                return true;
            }
        }   
        sender.sendMessage("Команда доступна только для игроков");
        return true;
    }   

    public boolean removeClan(Player player) {
        return clanManager.removeClan(player);
    }
}
