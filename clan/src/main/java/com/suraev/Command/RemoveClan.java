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
            
            if(!player.hasPermission("clan.remove")) {
                player.sendMessage("У вас нет прав на удаление клана");
                return true;
            }
            if(args.length == 1) {
                player.sendMessage("Укажите имя клана");
                return true;
            }
            if(args.length > 2) {
                player.sendMessage("Указаны лишние аргументы: используй /clan remove");
                return true;
            }
            if(args[0].equalsIgnoreCase("remove")) {
                String clanName = args[1];
                if(clanManager.getClanByName(clanName).isEmpty()) {
                    player.sendMessage("Клан с таким именем не найден");
                    return true;
                }
                clanManager.removeClanByName(clanName);
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
