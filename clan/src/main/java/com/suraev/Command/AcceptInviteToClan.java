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

            int lengthCommandArguments = strings.length;

            if(lengthCommandArguments == 0 ||lengthCommandArguments>1) {
                player.sendMessage("§cУкажите подкоманду accept, достаточно ввести §6/clan accept");
                return true;
            }

            if(lengthCommandArguments>1) {
                player.sendMessage("§cУказали лишние аргументы, достаточно ввести §6/clan accept");
                return true;
            }

            String acceptString = strings[0];
            if(!acceptString.equalsIgnoreCase("accept")) {
                player.sendMessage("§cПроверь, корректно ли ввел подкоманду accept. Должно быть §6/clan accept");
                return true;
            }

            Optional<InviteClanRequest> inviteRequest = clanInviteManager.getInviteRequest(player);

            if(!inviteRequest.isPresent()) {
                player.sendMessage("§cУ вас нет активных предложений");
                return true;
            }

            InviteClanRequest inviteClanRequest = inviteRequest.get();
            String nameOfClan = inviteClanRequest.getNameOfClan();


            if(clanInviteManager.removeInvite(player)) {
                clanManager.addClanMemberToClan(nameOfClan,player);
                player.sendMessage("§aВы приняты в клан "+nameOfClan);

                ClanMember sender = inviteClanRequest.getSender();
                Player inviter = Bukkit.getPlayer(sender.getUuid());
                if(inviter!= null) {
                    inviter.sendMessage("§aИгрок "+player.getName()+" вступил в клан "+nameOfClan);
                }
            }
            return true;
        }
        commandSender.sendMessage("§cТолько игрок может пользоваться данной командой");
        return true;
    }
}
