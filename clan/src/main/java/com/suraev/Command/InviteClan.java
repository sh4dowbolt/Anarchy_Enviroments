package com.suraev.Command;

import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class InviteClan implements CommandExecutor {

    private final ClanInviteManager clanInviteManager;
    private final ClanManager clanManager;

    public InviteClan(ClanInviteManager clanInviteManager, ClanManager clanManager) {
        this.clanInviteManager = clanInviteManager;
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player inviter) {

            if(strings.length<2) {
                inviter.sendMessage("§cИспользование: §6/clan invite <nickName>");
                return true;
            }

            if(strings.length>2) {
                inviter.sendMessage("§cУказали лишние аргументы, используй: §6/clan invite <nickName>" );
                return true;
            }
            //TODO: проверить, является ли игрок лидером или офицером клана || !clanManager.isPlayerOfficer(inviter)
            if(!clanManager.isPlayerClanLeader(inviter) ) {
                inviter.sendMessage("§cДля приглашения в клан, вы должны быть лидером или офицером клана");
                return true;
            }

            Optional<Clan> clanToInvite = clanManager.getClanByPlayer(inviter);

            if(clanToInvite.isEmpty()) {
                inviter.sendMessage("§cВозникли проблемы с кланом, обратитесь к администратору");
                return true;
            }

            String subCommand = strings[0];
            if(!subCommand.equalsIgnoreCase("invite")) {
                inviter.sendMessage("§cНеизвестная подкомада, используй: §6/clan invite <nickName> ");
                return true;
            }

            String nickName= strings[1];
            Player targetPlayer = commandSender.getServer().getPlayer(nickName);
            if(targetPlayer == null) {
                inviter.sendMessage("§cИгрок "+nickName+" оффлайн или его не существует!");
                return true;
            }
            if(inviter.getName().equals(targetPlayer.getName())) {
                inviter.sendMessage("§cВы не можете пригласить себя в клан");
                return true;
            }


            if(clanInviteManager.createInvite(clanToInvite.get(), inviter, targetPlayer)){
                inviter.sendMessage("§aПриглашение отправлено игроку §6"+targetPlayer.getName());
                targetPlayer.sendMessage("§aВас пригласили в клан §6"+clanToInvite.get().getTitle()+"\n" +
                        "§cЧтобы принять приглашение," +
                        "§cиспользуйте команду §6/clan accept. §cДля отказа используйте команду §6/clan cancel");
                return true;
            }

        } else {
            commandSender.sendMessage("§cКоманда доступна только для игроков");
        return true;
    }
    return false;
  }
}
