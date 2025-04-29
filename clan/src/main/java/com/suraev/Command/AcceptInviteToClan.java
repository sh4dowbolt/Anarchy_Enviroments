package com.suraev.Command;

import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanManager;
import com.suraev.Entity.ClanMember;
import com.suraev.Entity.DTO.ClanInviteManager;
import com.suraev.Event.InviteClanRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AcceptInviteToClan implements CommandExecutor {
    private final ClanManager clanManager;
    private final ClanInviteManager clanInviteManager;

    public AcceptInviteToClan(ClanManager clanManager, ClanInviteManager clanInviteManager) {
        this.clanManager = clanManager;
        this.clanInviteManager = clanInviteManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player player) {

            if(strings.length>1) {
                player.sendMessage("Указали лишние аргументы, достаточно ввести /clan accept");
                return true;
            }

            String acceptString = strings[0];
            if(!acceptString.equalsIgnoreCase("accept")) {
                player.sendMessage("Проверь, корректно ли ввел подкоманду accept. Должно быть /clan accept");
                return true;
            }

            Optional<InviteClanRequest> inviteRequest = clanInviteManager.getInviteRequest(player);

            if(!inviteRequest.isPresent()) {
                player.sendMessage("У вас нет активных предложений");
            }

            InviteClanRequest inviteClanRequest = inviteRequest.get();
            String nameOfClan = inviteClanRequest.getNameOfClan();


            if(clanInviteManager.acceptInvite(player)) {
                clanManager.addClanMemberToClan(nameOfClan,player);
                player.sendMessage("Вы приняты в клан "+nameOfClan);

                ClanMember sender = inviteClanRequest.getSender();
                Player inviter = Bukkit.getPlayer(sender.getUuid());
                if(inviter!= null) {
                    inviter.sendMessage("Игрок "+player.getName()+" вступил в клан "+nameOfClan);
                }
            }
            return true;
        }
        commandSender.sendMessage("Только игрок может пользоваться данной командой");
        return true;
    }
}
