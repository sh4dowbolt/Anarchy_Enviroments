package com.suraev.Command.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.suraev.Entity.Clan;
import com.suraev.ClanRunner;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanHome;
import java.util.Optional;
import org.bukkit.Location;





public class HomeSetClan implements CommandExecutor {
    private ClanManager clanManager;

    public HomeSetClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            
            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);

            if(!optionalClan.isPresent()) {
                player.sendMessage("Вы не состоите в клане, чтобы устанавливать точку респавна клана");
                return true;
            }
            Clan clan = optionalClan.get();

            Location location = player.getLocation();

            clan.setClanHome(location);
            player.sendMessage("Вы успешно установили точку респавна клана");
        }

        return false;
    }
}