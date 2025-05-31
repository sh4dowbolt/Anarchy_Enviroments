package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HelpClan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

      
        if(sender instanceof Player player) {
            
            if(args[0].equalsIgnoreCase("help")) {
                StringBuilder helpMessage = new StringBuilder();
                helpMessage.append("§c§lСправка по командам клана\n");
                helpMessage.append("§6/clan create <name> - §7создать клан\n");
                helpMessage.append("§6/clan invite <name> - §7пригласить в клан\n");
                helpMessage.append("§6/clan accept - §7принять приглашение\n");
                helpMessage.append("§6/clan cancel - §7отменить приглашение\n");
                helpMessage.append("§6/clan kick <name> - §7кикнуть из клана\n");
                helpMessage.append("§6/clan leave - §7покинуть клан\n");
                helpMessage.append("§6/clan info - §7информация о клане\n");
                helpMessage.append("§6/clan members - §7информация о участниках клана\n");
                helpMessage.append("§6/clan disband - §7распустить клан\n");
                helpMessage.append("§6/clan description <description> - §7установить описание клана\n");
                helpMessage.append("§6/clan officer <name> - §7назначить офицера\n");
                helpMessage.append("§6/clan unofficer <name> - §7снять с должности офицера\n");

                if(player.hasPermission("clan.remove")) {
                    helpMessage.append("§7/clan remove <name> - удалить клан\n");
                }

                player.sendMessage(helpMessage.toString());
                return true;
            }
        }
        sender.sendMessage("Эта команда доступна только для игроков");
        return false;
    }
}