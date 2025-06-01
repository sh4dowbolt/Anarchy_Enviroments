package com.suraev.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;    


import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;

public class ClanTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("clan")) {
            if(args.length == 1) {
                completions.add("create");
                completions.add("invite");
                completions.add("accept");
                completions.add("cancel");
                completions.add("kick");
                completions.add("leave");
                completions.add("info");
                completions.add("members");
                completions.add("disband");
                completions.add("description");
                completions.add("officer");
                completions.add("unofficer");
                completions.add("help");
            }
            if(args.length == 2) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    completions.add(player.getName());
                }
            }
            if(sender.hasPermission("clan.remove")) {
                completions.add("remove");
            }
        }
        return completions;
    }
}