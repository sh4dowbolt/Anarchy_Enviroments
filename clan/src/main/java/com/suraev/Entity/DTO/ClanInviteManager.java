package com.suraev.Entity.DTO;

import com.suraev.Entity.Clan;
import com.suraev.Event.InviteClanRequest;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ClanInviteManager {
    private final JavaPlugin javaPlugin;
    private final Map<PlayerDTO, InviteClanRequest> pendingInvites = new ConcurrentHashMap<>();
    private final Set<PlayerDTO> cooldownPlayers = ConcurrentHashMap.newKeySet();
    private final int inviteCooldownSeconds=30;

    public ClanInviteManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public boolean createInvite(Clan clan, Player inviter, Player targetTo) {
        PlayerDTO sender = new PlayerDTO(inviter.getName(), inviter.getUniqueId());
        PlayerDTO target = new PlayerDTO(targetTo.getName(), targetTo.getUniqueId());

        if(pendingInvites.containsKey(target) && !pendingInvites.get(target).isExpired()) {
            inviter.sendMessage("Игрок уже имеет активное приглашение");
            return false;
        }

        if(cooldownPlayers.contains(target)) {
            inviter.sendMessage("Нельзя спамить так много) чил");
            return false;
        }

        InviteClanRequest invite = new InviteClanRequest(
                clan.getTitle(),
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
}
