package com.suraev.discord.Command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            Component discordInfo = Component.text("Присоединяйся к нашему Discord серверу:").color(NamedTextColor.GRAY)
                    .appendNewline()
                    .append(Component.text("https://discord.com/invite/6vdAWSFr2B").color(NamedTextColor.DARK_AQUA))
                    .clickEvent(ClickEvent.openUrl("https://discord.com/invite/6vdAWSFr2B"));
            player.sendMessage(discordInfo);
            return true;
            

        }
        System.out.println("Только игрок может пользоваться данной командой");
        return false;
    }
}
