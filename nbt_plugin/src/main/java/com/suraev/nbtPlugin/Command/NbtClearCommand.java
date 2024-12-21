package com.suraev.nbtPlugin.Command;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NbtClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = itemInMainHand.getItemMeta();
            Material type = itemInMainHand.getType();
            String localizedName =getNormalName(type);

            if(type!=Material.AIR) {

                if(strings.length==0) {

                    NBT.modify(itemInMainHand, nbt -> {
                        nbt.modifyMeta(((readableNBT, meta) -> {
                            List<Component> lore = meta.lore();
                            if(lore == null) {
                                lore = new ArrayList<>();
                            }
                            lore.clear();
                            meta.lore(lore);
                            itemInMainHand.setItemMeta(meta);

                        }
                        ));
                        player.getInventory().setItemInMainHand(itemInMainHand);
                    });

                    itemMeta.setUnbreakable(false);
                    itemInMainHand.removeEnchantments();
                    player.getInventory().setItemInMainHand(itemInMainHand);


                    ItemMeta itemMeta1 = itemInMainHand.getItemMeta();
                    itemInMainHand.setItemMeta(itemMeta1);
                    NBT.modifyComponents(itemInMainHand,nbt -> {
                        if(nbt.hasTag("minecraft:custom_name")) {
                            nbt.setString("minecraft:custom_name", "{\"text\":\""+localizedName+"\"}");
                        }
                    });

                    player.getInventory().setItemInMainHand(itemInMainHand);

                    Component successMessage = Component.text("Предмет очищен от NBT тегов").color(NamedTextColor.GREEN);
                    player.sendMessage(successMessage);

                    return true;
                }

                Component shouldItem = Component.text("Команда должна использоваться без аргументов").color(NamedTextColor.DARK_RED);
                player.sendMessage(shouldItem);
                return true;
            }
            Component shouldItem = Component.text("В руках должен быть предмет").color(NamedTextColor.DARK_RED);
            player.sendMessage(shouldItem);
            return true;

        }
        return  false;
    }

    private String getNormalName(Material materialIn) {
        if(materialIn == null) return null;
        String s1 = materialIn.toString().replace("_", " ").toLowerCase(), s2 = "";
        s2 = s2 + s1.substring(0, 1).toUpperCase();
        for (int i = 1; i < s1.length(); i++) {
            if (" ".equals(s1.substring(i-1, i)))
                s2 = s2 + s1.substring(i, i+1).toUpperCase();
            else
                s2 = s2 + s1.substring(i, i+1); }

        return s2;
    }
}
