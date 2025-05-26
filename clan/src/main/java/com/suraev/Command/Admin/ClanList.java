package com.suraev.Command.Admin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.List;


public class ClanList implements CommandExecutor {

    private final ClanManager clanManager;

    public ClanList(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(args.length >1) {
            sender.sendMessage("Используй: /clan list");
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage("Эта команда доступна только для игроков");
            return true;
        }

        Player player = (Player) sender;

        List<ClanInfo> clansInfo = clanManager.getClansInfo();

        Component component = Component.text("Список кланов: ").color(NamedTextColor.AQUA);

        for(ClanInfo clanInfo : clansInfo) {
            


       return true;
            
    }
    
}
