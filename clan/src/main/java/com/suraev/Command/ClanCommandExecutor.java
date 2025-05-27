package com.suraev.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanInviteManager;
import com.suraev.Command.CreateClan;
import com.suraev.Command.InviteClan;
import com.suraev.Command.AcceptInviteToClan;
import com.suraev.Command.InviteCancel;
import com.suraev.Command.KickFromClan;
import com.suraev.Command.LeaveClan;
import com.suraev.Command.Admin.RemoveClan;
import com.suraev.Entity.Role;
import org.bukkit.entity.Player;

public class ClanCommandExecutor implements CommandExecutor{
    private final ClanManager clanManager;
    private final ClanInviteManager clanInviteManager;

    public ClanCommandExecutor(ClanManager clanManager, ClanInviteManager clanInviteManager) {
        this.clanManager = clanManager;
        this.clanInviteManager = clanInviteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
     
       if (args.length == 0) {
        sender.sendMessage("Используйте /clan help для получения списка команд");
        return true;
       }

       Player player = (Player) sender;

       switch (args[0].toLowerCase()) {
        case "create":
            return new CreateClan(clanManager).onCommand(sender, command, label, args);
        case "invite":
            return new InviteClan(clanInviteManager,clanManager).onCommand(sender, command, label, args);
        case "accept":
            return new AcceptInviteToClan(clanManager,clanInviteManager).onCommand(sender, command, label, args);
        case "cancel":
            return new InviteCancel(clanInviteManager,clanManager).onCommand(sender, command, label, args);
        case "kick":
            if(clanManager.hasPermission(player, Role.LEADER) || clanManager.hasPermission(player, Role.OFFICER)) {
                return new KickFromClan(clanManager).onCommand(sender, command, label, args);
            }
        case "leave":
            return new LeaveClan(clanManager).onCommand(sender, command, label, args);
        case "remove":
            return new RemoveClan(clanManager).onCommand(sender, command, label, args);
        case "info":
            return new ClanInfo(clanManager).onCommand(sender, command, label, args);
        case "members":
            return new MembersClan(clanManager).onCommand(sender, command, label, args);
        case "disband":
            return new DisbandClan(clanManager).onCommand(sender, command, label, args);
        case "officer":
            if(clanManager.hasPermission(player, Role.LEADER)) {
                return new OfficerClan(clanManager).onCommand(sender, command, label, args);
            }
        case "deofficer":
            if(clanManager.hasPermission(player, Role.LEADER)) {
                return new DeofficerClan(clanManager).onCommand(sender, command, label, args);
            }
        default:
            sender.sendMessage("Неверная подкоманда: используйте /clan help для получения списка команд");
            return false;
       }
    }
}
