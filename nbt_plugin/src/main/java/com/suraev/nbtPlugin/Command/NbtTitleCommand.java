package com.suraev.nbtPlugin.Command;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class NbtTitleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            // получаю айтем в руке
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material typeOfItem = itemInMainHand.getType();
            if (strings.length == 0) {
                Component ifNotNameOfItem = Component.text("Укажи наименование предмета").color(NamedTextColor.DARK_RED);
                player.sendMessage(ifNotNameOfItem);
                return true;
            }
            StringBuilder string = new StringBuilder();

            for (int i = 0; i < strings.length; i++) {
                String s1 = ChatColor.translateAlternateColorCodes('&', strings[i]);
                if (i == strings.length - 1) {
                    string.append(s1);
                } else {
                    string.append(s1).append(" ");
                }
            }
            
            if (typeOfItem != Material.AIR) {

                NBT.modifyComponents(itemInMainHand, nbt -> {
                    nbt.setString("minecraft:custom_name", "{\"text\":\""+string+"\"}");
                });

                String prepareString = string.toString();

                Component successMessage = Component.text("Установлено новое наименование: ")
                        .append(Component.text(prepareString).color(NamedTextColor.AQUA));
                player.sendMessage(successMessage);

            } else {
                Component ifNotItemMessage = Component.text("В руках должен быть предмет").color(NamedTextColor.DARK_RED);
                player.sendMessage(ifNotItemMessage);
            }
            return true;

        }
        System.out.println("Пользоваться данной командой может только игрок");
        return false;
    }
}




