package com.suraev.Command.Admin;

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
                player.sendMessage("§cУ вас нет прав на удаление клана");
                return true;
            }
            if(args.length == 1) {
                player.sendMessage("§cУкажите имя клана");
                return true;
            }
            if(args.length > 2) {
                player.sendMessage("§cУказаны лишние аргументы: используй §6/clan remove");
                return true;
            }
            if(args[0].equalsIgnoreCase("remove")) {
                Long clanId = Long.parseLong(args[1]);
                if(clanManager.getClanById(clanId).isEmpty()) {
                    player.sendMessage("§cКлан с таким именем не найден");
                    return true;
                }
                clanManager.removeClanById(clanId);
                player.sendMessage("§aКлан успешно удален");
                return true;              
            }
        }   
        sender.sendMessage("§cКоманда доступна только для игроков");
        return true;
    }   

    public boolean removeClan(Player player) {
        return clanManager.removeClan(player);
    }
}
