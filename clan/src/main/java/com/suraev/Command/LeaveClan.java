package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager; 
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;

public class LeaveClan implements CommandExecutor {

    private final ClanManager clanManager;

    public LeaveClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player player) {
                Clan clan = clanManager.getClanByPlayer(player);
                if(clan == null) {
                    player.sendMessage("Вы не состоите в клане");
                    return true;
                }
                ClanMember clanMember = new ClanMember(player);
                if(clan.isPlayerClanLeader(clanMember)) {
                    player.sendMessage("Вы не можете покинуть клан, пока являетесь лидером");
                    return true;
                }
                clanManager.removeClanMemberFromClan(player.getName(), player);
                player.sendMessage("Вы покинули клан "+clan.getTitle());
                return true;
            }
            sender.sendMessage("Только игроки могут покинуть клан");
            return true;
     }
}

