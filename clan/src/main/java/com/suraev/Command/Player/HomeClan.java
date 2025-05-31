package com.suraev.Command.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.suraev.Entity.Clan;
import com.suraev.ClanRunner;
import com.suraev.Entity.ClanManager;
import java.util.Optional;
import org.bukkit.Location;

public class HomeClan implements CommandExecutor {
    private ClanManager clanManager;

    public HomeClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);

            if(!optionalClan.isPresent()) {
                player.sendMessage("Вы не состоите в клане, чтобы использовать команду /clan home");
                return true;
            }
            Clan clan = optionalClan.get();

            if(clan.getClanHome() == null) {
                player.sendMessage("Вы не установили точку респавна клана, чтобы использовать команду /clan home");
                return true;
            }

            Location location = clan.getClanHome();

            player.teleport(location);
            player.sendMessage("Вы были телепортированы на точку респавна клана");
        }
        return false;
    }
}