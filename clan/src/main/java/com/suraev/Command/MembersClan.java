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
import net.kyori.adventure.text.Component;

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

                List<Component> leaders = clan.getMembers().stream().filter(member -> member.getRole().equals(Role.LEADER)).map(member -> {
                    return member.toStringComponent();
                }).collect(Collectors.toList());
                List<Component> officers = clan.getMembers().stream().filter(member -> member.getRole().equals(Role.OFFICER)).map(member -> {
                    return member.toStringComponent();
                }).collect(Collectors.toList());
                List<Component> members = clan.getMembers().stream().filter(member -> member.getRole().equals(Role.MEMBER)).map(member -> {
                    return member.toStringComponent();
                }).collect(Collectors.toList());
                
                
                
                Component leadersMessage = buildRoleMessage("Лидер клана: ", leaders);
                Component officersMessage = buildRoleMessage("Офицеры клана: ", officers);
                Component membersMessage = buildRoleMessage("Участники клана: ", members);


                Component message = leadersMessage
                .append(Component.newline())
                .append(officersMessage)
                .append(Component.newline())
                .append(membersMessage);

                player.sendMessage(message);
                return true;
            }
        }
        sender.sendMessage("Команда доступна только для игроков");
        return true;

     
    }

    private Component buildRoleMessage(String prefix, List<Component> components) {
        Component message = Component.text(prefix);

        if(components.isEmpty()) {
            message = message.append(Component.text("Отсутствуют"));
            return message;
        }

        for(Component component : components) {
            message = message.append(component);
            if(components.indexOf(component) != components.size() - 1) {
                message = message.append(Component.text(", "));
            }
        }
        return message;
    }

}
