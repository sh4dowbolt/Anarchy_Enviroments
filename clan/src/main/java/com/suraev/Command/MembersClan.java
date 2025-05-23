package com.suraev.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import com.suraev.Entity.ClanManager;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanMember;
import com.suraev.Entity.Role;

import java.util.stream.Collectors;
import java.util.Optional;
import com.suraev.Entity.Clan;
import java.util.List;
import java.util.ArrayList;
import com.suraev.Entity.DTO.ClanMemberInfo;

public class MembersClan implements CommandExecutor {
    private final ClanManager clanManager;

    public MembersClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(args.length != 1) {
                player.sendMessage("Использование: /clan members <name>");
                return true;
            }
            
            if(args[0].equalsIgnoreCase("members")) {
                Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
                
                if(optionalClan.isEmpty()) {
                    player.sendMessage("Вы не находитесь в клане");
                    return true;
                }
                Clan clan = optionalClan.get();
                clan.getMembers().stream().map(member -> {
                    ClanMemberInfo info = new ClanMemberInfo();
                    info.setName(member.getName());
                    info.setRole(member.getRole().toString());
                    info.setOnlineStatus(member.isOnline() ? "Онлайн" : "Офлайн");
                    return info;
                }).collect(Collectors.toList());
                
                StringBuilder message = new StringBuilder();
                message.append("Лидер клана: ");
                message.append(clan.getMembers().stream().filter(member -> member.getRole().equals(Role.LEADER)).map(ClanMember::getName).collect(Collectors.joining(", ")));
                message.append("\n");
                message.append("Офицеры клана: ");
                message.append(clan.getMembers().stream().filter(member -> member.getRole().equals(Role.OFFICER)).map(ClanMember::getName).collect(Collectors.joining(", ")));
                message.append("\n");
                message.append("Участники клана: ");
                message.append(clan.getMembers().stream().filter(member -> member.getRole().equals(Role.MEMBER)).map(ClanMember::getName).collect(Collectors.joining(", ")));
        
                player.sendMessage(message.toString());
                return true;
            }
        }
        sender.sendMessage("Команда доступна только для игроков");
        return true;

     
    }
}
