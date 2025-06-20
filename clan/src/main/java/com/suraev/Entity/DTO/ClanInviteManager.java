package com.suraev.Entity.DTO;

import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;
import com.suraev.Event.InviteClanRequest;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ClanInviteManager {
    
    private final JavaPlugin plugin;
    private final Map<ClanMember, InviteClanRequest> pendingInvites = new ConcurrentHashMap<>();
    private final Set<ClanMember> cooldownPlayers = ConcurrentHashMap.newKeySet();
    private final int inviteCooldownSeconds=30;

    public ClanInviteManager(JavaPlugin javaPlugin) {
        this.plugin = javaPlugin;
    }

    public Optional<InviteClanRequest> getInviteRequest(Player player) {
        ClanMember clanMember = new ClanMember(player);
        return Optional.ofNullable(pendingInvites.get(clanMember));
    }

    public boolean createInvite(Clan clan, Player inviter, Player targetTo) {
        ClanMember sender = new ClanMember(inviter);
        ClanMember target = new ClanMember(targetTo);

        if(pendingInvites.containsKey(target) && !requestIsExpired(pendingInvites.get(target))) {
            inviter.sendMessage("§cИгрок уже имеет активное приглашение");
            return false;
        }

        if(cooldownPlayers.contains(target)) {
            inviter.sendMessage("§cВы не можете приглашать игроков слишком часто");
            return false;
        }

        InviteClanRequest invite = new InviteClanRequest(
                clan,
                sender,
                target,
                2,
                TimeUnit.MINUTES
                );
        pendingInvites.put(target,invite);
        cooldownPlayers.add(target);

        new BukkitRunnable() {

            @Override
            public void run() {
                cooldownPlayers.remove(target);
            }
        }.runTaskLater(plugin, inviteCooldownSeconds*20L);

        return  true;
    }

    public boolean removeInvite(Player player) {
        ClanMember clanMember = new ClanMember(player);
        InviteClanRequest invite = pendingInvites.get(clanMember);

        if(invite == null || requestIsExpired(invite)) {
            player.sendMessage("§cПриглашение истекло или его не существует");
            return false;
        }

        pendingInvites.remove(clanMember);
        return true;
    }

    public void cleanUpExpired() {
        pendingInvites.entrySet().removeIf(entry -> requestIsExpired(entry.getValue()));
    }

    private boolean requestIsExpired(InviteClanRequest invite) {
        return invite.isExpired();
    }
}
