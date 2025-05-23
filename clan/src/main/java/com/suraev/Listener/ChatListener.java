package com.suraev.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.suraev.Entity.ClanManager;
import org.bukkit.entity.Player;
import com.suraev.Entity.Clan;

public class ChatListener implements Listener {

    private final ClanManager clanManager;

    public ChatListener(ClanManager clanManager) {
        this.clanManager = clanManager;
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Clan clan = clanManager.getClanByPlayer(player);
        if(clan != null) {
            event.setFormat(clan.getTitle() + " §7: §f" + player.getName() + " §7: §f" + event.getMessage());
        }
    }
}
