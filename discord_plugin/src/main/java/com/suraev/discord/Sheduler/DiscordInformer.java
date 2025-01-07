package com.suraev.discord.Sheduler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class DiscordInformer implements Runnable{
    @Override
    public void run() {
        Component discordInfo = Component.text("Присоединяйся к нашему Discord серверу:").color(NamedTextColor.GRAY)
                .appendNewline()
                .append(Component.text("https://discord.gg/GJtHtrkM").color(NamedTextColor.DARK_AQUA))
                .appendNewline()
                .append(Component.text("Также посети ").color(NamedTextColor.GRAY))
                .append(Component.text("/warp info")).color(NamedTextColor.GOLD)
                .append(Component.text(" и ").color(NamedTextColor.GRAY))
                .append(Component.text("/warp core").color(NamedTextColor.DARK_AQUA))
                .clickEvent(ClickEvent.openUrl("https://discord.com/invite/6vdAWSFr2B"));
        Bukkit.broadcast(discordInfo);
    }
}
