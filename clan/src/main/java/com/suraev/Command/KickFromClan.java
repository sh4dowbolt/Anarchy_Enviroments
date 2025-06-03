package com.suraev.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.suraev.Entity.ClanManager;
import org.bukkit.entity.Player;
import com.suraev.Entity.ClanMember;
import com.suraev.Entity.Clan;
import java.util.Optional;

public class KickFromClan implements CommandExecutor{

    private final ClanManager clanManager;

    public KickFromClan(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {

            if(args.length != 2) {
                player.sendMessage("§cИспользуй: §6/clan kick <ник>");
                return true;
            }
            String subCommand = args[0];
            if(!subCommand.equalsIgnoreCase("kick")) {
                player.sendMessage("§cНеверно указана подкоманда: используй §6/clan kick для кика из клана");
                return true;
            }
            String playerName = args[1];
            Player targetPlayer = Bukkit.getPlayer(playerName);
            if(targetPlayer == null) {
                player.sendMessage("§cИгрок §6"+playerName + " §cне найден");
                return true;
            }
            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
            if(optionalClan.isEmpty()) {
                player.sendMessage("§cВы не состоите в клане");
                return true;
            }
            
            Clan clan = optionalClan.get();

            ClanMember targetClanMember = new ClanMember(targetPlayer);
            ClanMember currentClanMember = new ClanMember(player);
            ClanMember clanLeader = clan.getOwner();
            
            if(clan.isPlayerClanLeader(currentClanMember)) {       

            if(clanLeader.equals(targetClanMember)) {
                player.sendMessage("§cВы не можете кикнуть себя из клана, прежде правозгласите нового лидера");
                return true;
            }

            if(clan.isPlayerInClan(targetClanMember)) {
                clanManager.removeClanMemberFromClan(clan.getId(), targetPlayer);
                player.sendMessage("§aИгрок §6"+playerName + " §aуспешно кикнут из клана §6"+clan.getTitle());
                targetPlayer.sendMessage("§aВы были кикнуты из клана §6"+clan.getTitle());
                return true;
            }
            player.sendMessage("§cУказанный игрок не состоит в клане");
            return true;
        }
        sender.sendMessage("§cДля использования команды необходимо быть лидером клана");
        return true;
    }
    sender.sendMessage("§cКоманда доступна только для игроков");
        return true;
}
}