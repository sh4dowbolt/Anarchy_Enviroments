package com.suraev.discord.Sheduler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class DiscordInformer implements Runnable{
    @Override
    public void run() {
        Component discordInfo = Component.text("Игрок! Чтобы не пропустить последние новости и быть в курсе событий," +
                        " присоединяйтесь к нам в discord\nhttps://discord.gg/NdcT9gA7")
                .color(NamedTextColor.GREEN)
                .clickEvent(ClickEvent.openUrl("https://discord.gg/NdcT9gA7"));
        Bukkit.broadcast(discordInfo);
    }
}
