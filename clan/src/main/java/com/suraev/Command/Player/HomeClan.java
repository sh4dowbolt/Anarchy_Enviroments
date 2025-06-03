package com.suraev.Command.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.earth2me.essentials.User;
import com.google.common.escape.Escaper;
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanManager;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Location;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.AsyncTeleport;



public class HomeClan implements CommandExecutor {
    private ClanManager clanManager;
    private Essentials ess;
    private Long delayTeleportCauseCombat = 10L;
 
    public HomeClan(ClanManager clanManager, Essentials ess) {
        this.clanManager = clanManager;
        this.ess = ess;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) 
        {
            Player player = (Player) sender;
     
            User user = ess.getUser(player);

            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);

            if(!optionalClan.isPresent()) {
                player.sendMessage("§cВы не состоите в клане, чтобы использовать команду §6/clan home");
                return true;
            }
            Clan clan = optionalClan.get();

            if(clan.getClanHome() == null) {
                player.sendMessage("§cВы не установили точку респавна клана, чтобы использовать команду §6/clan home");
                return true;
            }
            if(user.hasTakenDamage(delayTeleportCauseCombat)) {
                player.sendMessage("§cВы не можете использовать команду §6/clan home, пока вы находитесь в бою");
                return true;
            }

            Location location = clan.getClanHome();

            CompletableFuture<Boolean> future = new CompletableFuture<>();
            
            try {
                AsyncTeleport asyncTeleport = user.getAsyncTeleport();
                asyncTeleport.teleport(location, null, TeleportCause.PLUGIN, future);

            future.thenAccept(success -> {
                if(success) {
                    player.sendMessage("§aВы были телепортированы на точку респавна клана");
                } else {
                    player.sendMessage("§cТелепорт отменена");
                }
            }).exceptionally(e -> {
                player.sendMessage("§cОшибка при телепортировании на точку респавна клана");
                return null;
            });
        }  catch (Exception e) {
            player.sendMessage("§cОшибка: " + e.getMessage());
            return true;
        }

        return false;
        }
        sender.sendMessage("§cЭта команда может использоваться только в игре");
        return true;
    }
}

