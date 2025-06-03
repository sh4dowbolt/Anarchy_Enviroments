package com.suraev.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanManager; 
import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;
import java.util.Optional;

public class LeaveClan implements CommandExecutor {

    private final ClanManager clanManager;

    public LeaveClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player player) {
                Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
                if(optionalClan.isEmpty()) {
                    player.sendMessage("§cВы не состоите в клане");
                    return true;
                }
                Clan clan = optionalClan.get();
                ClanMember clanMember = new ClanMember(player);
                if(clan.isPlayerClanLeader(clanMember)) {
                    player.sendMessage("§cВы не можете покинуть клан, пока являетесь лидером");
                    return true;
                }
                clanManager.removeClanMemberFromClan(clan.getId(), player);
                player.sendMessage("§aВы покинули клан "+clan.getTitle());
                return true;
            }
            sender.sendMessage("§cТолько игроки могут покинуть клан");
            return true;
     }
}

