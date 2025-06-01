package com.suraev.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager;
import org.bukkit.Bukkit;
import com.suraev.Entity.Clan;
import java.util.Optional;
import com.suraev.Entity.Role;
import java.util.UUID;

public class DeofficerClan implements CommandExecutor {

    private final ClanManager clanManager;

    public DeofficerClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }
    
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player player) {
                if(args.length == 2) {
                 if(args.length > 2) {
                    player.sendMessage("§cУказаны лишние аргументы: используй §6/clan deofficer <playerName>");
                    return true;
                 }   
        
                    String playerName = args[1];
                    Player target = Bukkit.getPlayer(playerName);
                    if(target == null) {
                        player.sendMessage("§cИгрок с таким именем не найден");
                        return true;
                    }
                    Optional<Clan> optionalClan = clanManager.getClanByPlayer(target);
                    if(optionalClan.isEmpty()) {
                        player.sendMessage("§c§lИгрок не в клане");
                        return true;
                    }
                    Clan clan = optionalClan.get();
                    Long clanId = clan.getId();
                    
                    clanManager.updateClanMemberRole(clanId, target, Role.MEMBER);

                    player.sendMessage("§aВы сняли " + target.getName() + " с должности офицера клана");
                    target.sendMessage("§aВы были сняты с должности офицера клана " + clan.getTitle() + " лидером клана " + player.getDisplayName());
                    return true;
                }
            }
            return false;
        }
    }

