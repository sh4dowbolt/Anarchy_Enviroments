package com.suraev.nbtPlugin.Command.util;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Unbreakable {

    public static ItemStack handle(ItemStack itemInMainHand) {
        return NBT.modify(itemInMainHand, nbt -> {
            nbt.setBoolean("Unbreakable", true);
            nbt.modifyMeta((readableNBT, meta) -> {

                List<Component> lore = meta.lore();
                if (lore == null) {
                    lore = new ArrayList<>();
                }
                Component titleOfUnbreakable = Component.text("Unbreakable")
                        .color(NamedTextColor.DARK_RED);
                lore.addLast(titleOfUnbreakable);
                meta.lore(lore);
                itemInMainHand.setItemMeta(meta);
            });
            return itemInMainHand;
        } );
    }
}
