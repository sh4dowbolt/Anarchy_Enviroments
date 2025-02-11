package com.suraev.discord.Command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            Component voteInfo = Component.text("Проголосовать за сервер").color(NamedTextColor.GRAY)
                    .appendNewline()
                    .append(Component.text("https://hotmc.ru/vote-274393").color(NamedTextColor.DARK_AQUA)
                            .clickEvent(ClickEvent.openUrl("https://hotmc.ru/vote-274393")))
                    .appendNewline()
                    .append(Component.text("https://minecraftrating.ru/server/lambdacoreanarchy/#")
                            .color(NamedTextColor.DARK_AQUA)
                            .clickEvent(ClickEvent.openUrl("https://minecraftrating.ru/server/lambdacoreanarchy/#")));
            player.sendMessage(voteInfo);
            return true;
        }
        System.out.println("Только игрок может пользоваться данной командой");
        return false;
    }
}
