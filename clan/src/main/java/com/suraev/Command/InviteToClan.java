package com.suraev.Command;

import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.ClanMember;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class InviteToClan implements CommandExecutor {

    private final ClanInviteManager clanInviteManager;
    private final ClanManager clanManager;

    public InviteToClan(ClanInviteManager clanInviteManager, ClanManager clanManager) {
        this.clanInviteManager = clanInviteManager;
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player inviter) {

            if(strings.length<2) {
                inviter.sendMessage("Использование: /clan invite <nickName>");
                return true;
            }

            if(strings.length>2) {
                inviter.sendMessage("Указали лишние аргументы, используй: /clan invite <nickName>" );
                return true;
            }

            if(!clanManager.isPlayerClanLeader(inviter)) {
                inviter.sendMessage("Прежде чем кого то пригласить, создай клан командой: /clan create <nameOfClan>");
            }

            Clan inviterClan = clanManager.getClanByPlayer(inviter);

            if(inviterClan == null) {
                inviter.sendMessage("Возникли проблемы с кланом, обратитесь к администратору");
                return true;
            }


            String subCommand = strings[0];
            if(!subCommand.equalsIgnoreCase("create")) {
                inviter.sendMessage("Неизвестная подкомада, используй: /clan invite <nickName> ");
                return true;
            }

            String nickName= strings[1];
            Player targetPlayer = commandSender.getServer().getPlayer(nickName);
            if(targetPlayer == null) {
                inviter.sendMessage("Игрок "+nickName+" оффлайн или его не существует!");
                return true;
            }

            if(clanInviteManager.createInvite(inviterClan, inviter, targetPlayer)){
                inviter.sendMessage("Приглашение отправлено игроку "+targetPlayer.getName());
                targetPlayer.sendMessage("Вас пригласили в клан "+inviterClan.getTitle()+" Чтобы принять приглашение," +
                        "используйте команду /clan accept");
            }

        }
            commandSender.sendMessage("Команда доступна только для игроков");
        return true;
    }
}
