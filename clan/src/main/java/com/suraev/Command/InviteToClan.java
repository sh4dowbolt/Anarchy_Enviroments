package com.suraev.Command;

import com.suraev.Entity.ClanManager;
import com.suraev.Entity.DTO.ClanInviteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class InviteToClan implements CommandExecutor {

    private final ClanInviteManager clanInviteManager;
    private final ClanManager clanManager;

    public InviteToClan(ClanInviteManager clanInviteManager, ClanManager clanManager) {
        this.clanInviteManager = clanInviteManager;
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
