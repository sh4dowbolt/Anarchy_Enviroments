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
                player.sendMessage("Используй: /clan kick <ник>");
                return true;
            }
            String subCommand = args[0];
            if(!subCommand.equalsIgnoreCase("kick")) {
                player.sendMessage("Неверно указана подкоманда: используй /clan kick для кика из клана");
                return true;
            }
            String playerName = args[1];
            Player targetPlayer = Bukkit.getPlayer(playerName);
            if(targetPlayer == null) {
                player.sendMessage("Игрок " + playerName + " не найден");
                return true;
            }
            Optional<Clan> optionalClan = clanManager.getClanByPlayer(player);
            if(optionalClan.isEmpty()) {
                player.sendMessage("Вы не состоите в клане");
                return true;
            }
            
            Clan clan = optionalClan.get();

            ClanMember targetClanMember = new ClanMember(targetPlayer);
            ClanMember currentClanMember = new ClanMember(player);
            ClanMember clanLeader = clan.getOwner();
            
            if(clan.isPlayerClanLeader(currentClanMember)) {       

            if(clanLeader.equals(targetClanMember)) {
                player.sendMessage("Вы не можете кикнуть себя из клана, прежде правозгласите нового лидера");
                return true;
            }

            if(clan.isPlayerInClan(targetClanMember)) {
                clanManager.removeClanMemberFromClan(playerName, player);
                player.sendMessage("Игрок " + playerName + " успешно кикнут из клана"+clan.getTitle());
                targetPlayer.sendMessage("Вы были кикнуты из клана "+clan.getTitle());
                return true;
            }
            player.sendMessage("Указанный игрок не состоит в клане");
            return true;
        }
        sender.sendMessage("Для использования команды необходимо быть лидером клана");
        return true;
    }
    sender.sendMessage("Команда доступна только для игроков");
        return true;
}
}