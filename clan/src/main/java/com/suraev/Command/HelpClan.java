package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.ArrayList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;


public class HelpClan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> commands = new ArrayList<>();
        commands.add("§c§lСправка по командам клана\n");
        commands.add("§6/clan create <name> - §7создать клан");
        commands.add("§6/clan invite <name> - §7пригласить в клан");
        commands.add("§6/clan accept - §7принять приглашение");
        commands.add("§6/clan cancel - §7отменить приглашение");
        commands.add("§6/clan kick <name> - §7кикнуть из клана");
        commands.add("§6/clan leave - §7покинуть клан");
        commands.add("§6/clan info - §7информация о клане");
        commands.add("§6/clan members - §7информация о участниках клана");
        commands.add("§6/clan disband - §7распустить клан");
        commands.add("§6/clan description <description> - §7установить описание клана");
        commands.add("§6/clan officer <name> - §7назначить офицера");
        commands.add("§6/clan unofficer <name> - §7снять с должности офицера");
        commands.add("§6/clan sethome - §7установить точку респавна клана");
        commands.add("§6/clan home - §7переместиться в точку респавна клана");

        if(sender instanceof Player player) {

            if(args[0].equalsIgnoreCase("help")) {
                int page=1;
                int pageSize = 10;
                int totalPages = (int) Math.ceil( (double)commands.size()/pageSize);

                if(args.length == 2) {
                    try {
                        page = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cИспользуй: §6/clan help <номер страницы>");
                        return true;
                    }
                }

                if(player.hasPermission("clan.remove")) {
                    commands.add("§7/clan remove <name> - удалить клан\n");
                }

                List<String> pageCommands = commands
                .subList((page-1)*10, Math.min(page*10, commands.size()));

                Component message = Component.text("§c§lСправка по командам клана\n");

                for(String commandText : pageCommands) {
                    message = message.append(Component.text(commandText).color(NamedTextColor.AQUA));
                }

                if(page < totalPages) {
                    message = message.append(Component.newline())
                    .append(Component.text("§cДля просмотра следующей страницы используй: §6/clan help " + (page+1)).color(NamedTextColor.AQUA));
                }

              

                player.sendMessage(message);
                return true;
            }
        }
        sender.sendMessage("Эта команда доступна только для игроков");
        return false;
    }
}