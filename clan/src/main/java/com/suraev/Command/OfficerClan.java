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



public class OfficerClan implements CommandExecutor {

    private final ClanManager clanManager;

    public OfficerClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
           if(args.length == 2) {
             if(args.length > 2) {
                player.sendMessage("§cУказаны лишние аргументы: используй §6/clan officer <playerName>");
                return true;
            }
            

            if(args.length == 1) {
                player.sendMessage("§cНедостаточно аргументов: используй §6/clan officer <playerName>");
                return true;
            }

            String playerName = args[1];
            Player target = Bukkit.getPlayer(playerName);
            if(target == null) {
                player.sendMessage("§cИгрок с таким именем не найден");
                return true;
            }
            Optional<Clan> optionalClan = clanManager.getClanByPlayer(target);)

            if(optionalClan.isEmpty()) {
                player.sendMessage("§cИгрок не в клане");
                return true;
            }
            Clan clan = optionalClan.get();
            Long clanId = clan.getId();

            if(clanManager.isPlayerClanLeader(player)) {
                player.sendMessage("§cВы не можете назначить себе офицером, будучи лидером клана");
                return true;
            }
            
            
            clanManager.updateClanMemberRole(clanId, target, Role.OFFICER);

            player.sendMessage("§aВы успешно назначили " + target.getName() + " офицером клана");
            target.sendMessage("§aВы были назначены офицером клана " + clan.getTitle() + " лидером клана " + player.getDisplayName());
            return true;
                
            }
            player.sendMessage("§cУказаны лишние аргументы: используй §6/clan officer <playerName>");
            return false;
        }
        sender.sendMessage("§cЭта команда доступна только для игроков");
        return false;
    }
}
