package com.suraev.Command.Player;

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
        
        if(args.length >2) {
            sender.sendMessage("§cПроверьте правильность ввода команды: §6/clan list <страница>");
            return true;
        }

        if(args[0].equalsIgnoreCase("list")) {

            Player player = (Player) sender;
            int page = 1;
            int pageSize = 10;

            int totalPages = (int) Math.ceil( (double)clanManager.getClansSize()/pageSize);

            if(args.length == 2) {
                try {
                    page = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("§cИспользуй: §6/clan list <страница>");
                    return true;
                }
            }

            List<ClanInfo> clansInfo = clanManager.getClansInfo(page, pageSize);

            if(clansInfo.isEmpty()) {
                sender.sendMessage("§cСтраница " + page + " не найдена");
                return true;
            }
            

            Component message = Component.text("Список кланов: ").color(NamedTextColor.AQUA);
            message = message.append(Component.newline())
            .append(Component.text("ID клана").color(NamedTextColor.GOLD))
            .append(Component.text("  "))
            .append(Component.text("Название клана").color(NamedTextColor.AQUA));

            for(ClanInfo clanInfo : clansInfo) {
                message = message.append(Component.newline().append(clanInfo.toComponent()));
            }

            if(page < totalPages) {
                message = message.append(Component.newline()
                .append(Component.text("§cИспользуйте §6/clan list "+(page+1)+" §cдля перехода на другую страницу").color(NamedTextColor.AQUA)));
            }

            player.sendMessage(message);
            return true;
        }  
        sender.sendMessage("Эта команда доступна только для игроков");
        return false;
    }


}
