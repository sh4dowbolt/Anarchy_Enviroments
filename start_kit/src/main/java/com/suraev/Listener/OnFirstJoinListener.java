package com.suraev.Listener;

import com.suraev.Utils.Configuration;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteItemNBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OnFirstJoinListener implements Listener {
    private final FileConfiguration fileConfiguration;
    public OnFirstJoinListener(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()) {

            ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
            String typeFromConfig = fileConfiguration.getString("settings.type-kit");
            String playerName = player.getName();

            String commandForConsole = "kit " + typeFromConfig + " ";

            Bukkit.dispatchCommand(consoleSender, commandForConsole +playerName);
        }
    }


}
