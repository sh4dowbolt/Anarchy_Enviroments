package com.suraev.discord.Sheduler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class DiscordInformer implements Runnable{
    @Override
    public void run() {
        Component discordInfo = Component.text("Игрок! Чтобы не пропустить последние новости и быть в курсе событий," +
                        " присоединяйтесь к нам в discord\nhttps://discord.com/invite/6vdAWSFr2B")
                .color(NamedTextColor.GREEN)
                .clickEvent(ClickEvent.openUrl("https://discord.com/invite/6vdAWSFr2B"));
        Bukkit.broadcast(discordInfo);
    }
}
