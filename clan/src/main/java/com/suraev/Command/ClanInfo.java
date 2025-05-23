package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;
import com.suraev.Entity.Role;

import java.util.stream.Collectors;
import java.util.Optional;
import org.bukkit.entity.Player;

public class ClanInfo implements CommandExecutor {
    private final ClanManager clanManager;

    public ClanInfo(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            sender.sendMessage("Использование: /clan info ");
            return true;
        }

        if(!(sender instanceof Player player)) {
            sender.sendMessage("Команда доступна только для игроков");
            return true;
        }
        Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
        if(optionalClan.isEmpty()) {
            sender.sendMessage("Для получения информации о клане, вы должны быть в клане");
            return true;
        }

        Clan clan = optionalClan.get();

        StringBuilder message = new StringBuilder();

        message.append("Информация о клане: ").append(clan.getTitle()).append("\n")
        .append("Описание: ").append(clan.getDescription()).append("\n");

        message.append("Лидер клана: ").append(clan.getOwner().getName()).append("\n");
    
        sender.sendMessage(message.toString());
        return true;
    }
}