package com.suraev.nbtPlugin.Command;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentIteratorFlag;
import net.kyori.adventure.text.ComponentIteratorType;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.Range;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class NbtDesc implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material typeOfItem = itemInMainHand.getType();
            if (typeOfItem != Material.AIR) {
                int length = strings.length;
                if(length!=0) {
                    StringBuilder description = new StringBuilder();
                    for (int i = 0; i < strings.length; i++) {
                        String s1 = ChatColor.translateAlternateColorCodes('&', strings[i]);
                        if (i == strings.length - 1) {
                            description.append(s1);
                        } else {
                            description.append(s1).append(" ");
                        }
                    }
                    String descriptionString = description.toString();
                    NBT.modify(itemInMainHand, nbt -> {
                        nbt.modifyMeta(((readableNBT, meta) -> {
                            List<Component> lore = meta.lore();
                            if (lore == null) {
                                lore = new ArrayList<>();
                            }



                            for (String string : descriptionString.split("\\.",20)) {
                                TextComponent data = Component.text(string)
                                        .style(Style.style(TextDecoration.ITALIC))
                                        .color(NamedTextColor.GRAY);
                                lore.addLast(data);
                            }

                    /*        TextComponent textForLore = Component.text(descriptionString)
                                    .style(Style.style(TextDecoration.ITALIC))
                                    .color(NamedTextColor.GRAY);
*/


                            meta.lore(lore);
                            itemInMainHand.setItemMeta(meta);
                            player.getInventory().setItemInMainHand(itemInMainHand);
                        }));
                    });
                    Component success = Component.text("Успешно добавлено описание к предмету: ").color(NamedTextColor.GREEN)
                            .append(Component.text(descriptionString));
                    player.sendMessage(success);
                    return true;
                }
                Component emptyInput = Component.text("Ошибка! Введите описание предмета").color(NamedTextColor.DARK_RED);
                player.sendMessage(emptyInput);
                return true;

            }
            Component shouldItem = Component.text("В руках должен быть предмет").color(NamedTextColor.DARK_RED);
            player.sendMessage(shouldItem);
            return true;

        }

        return false;
    }
}
