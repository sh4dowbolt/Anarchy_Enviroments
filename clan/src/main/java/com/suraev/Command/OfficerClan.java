package com.suraev.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.Clan;
import com.suraev.Entity.Role;
import java.util.Optional;
import com.suraev.Entity.ClanMember;
import java.util.UUID;


public class OfficerClan implements CommandExecutor {

    private final ClanManager clanManager;

    public OfficerClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 1) {
             if(args.length > 2) {
                player.sendMessage("Указаны лишние аргументы: используй /clan officer <playerName>");
                return true;
             }   
                if(args[0].equalsIgnoreCase("officer")) {

                    String playerName = args[1];
                    Player target = Bukkit.getPlayer(playerName);
                    if(target == null) {
                        player.sendMessage("Игрок с таким именем не найден");
                        return true;
                    }
                    Optional<Clan> optionalClan = clanManager.getClanByPlayer(target);
                    if(optionalClan.isEmpty()) {
                        player.sendMessage("Игрок не в клане");
                        return true;
                    }
                    Clan clan = optionalClan.get();
                    UUID clanId = clan.getId();
                    
                    clanManager.updateClanMemberRole(clanId, target, Role.OFFICER);

                    player.sendMessage("Вы успешно назначили " + target.getName() + " офицером клана");
                    target.sendMessage("Вы были назначены офицером клана " + clan.getTitle() + " лидером клана " + player.getDisplayName());
                    return true;
                }

            }
        }
        return false;
    }
}
