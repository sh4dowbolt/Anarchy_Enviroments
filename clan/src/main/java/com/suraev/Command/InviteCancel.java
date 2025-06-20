package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanInviteManager;

public class InviteCancel implements CommandExecutor {

    private final ClanInviteManager clanInviteManager;
    private final ClanManager clanManager;

    public InviteCancel(ClanInviteManager clanInviteManager, ClanManager clanManager) {
        this.clanInviteManager = clanInviteManager;
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            int argCommandLength = args.length;
            if(argCommandLength == 0) {
                player.sendMessage("§cНеверно указана подкоманда: используй §6/help §cдля получения списка команд");
                return true;
            }
            if(argCommandLength > 1) {
                player.sendMessage("§cУказаны лишние аргументы: используй §6/clan cancel");
                return true;
            }
            if(args[0].equalsIgnoreCase("cancel")) {
                clanInviteManager.removeInvite(player);
                return true;
            }else{
                player.sendMessage("§cУ вас нет приглашения в клан");
                return true;
            }

        }
        return false;
    }

}
