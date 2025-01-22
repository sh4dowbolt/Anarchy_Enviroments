package com.suraev.nbtPlugin.Listeners;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteItemNBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KillCounter implements Listener {

    @EventHandler
    public void onPLayerDeath(EntityDeathEvent event) {
        if(event.getEntity().getKiller() instanceof Player player) {

            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            LivingEntity typeOfDeadEntity = event.getEntity();
            Material type = itemInMainHand.getType();

            if (type != Material.AIR) {

                if (typeOfDeadEntity instanceof Animals) {
                    NBT.modify(itemInMainHand, nbt -> {
                        increaseAnimalCounter(player, nbt, itemInMainHand);
                    });
                }
                if (typeOfDeadEntity instanceof Enemy) {
                    NBT.modify(itemInMainHand, nbt -> {
                        increaseMonsterCounter(player, nbt, itemInMainHand);
                    });
                }
                if (typeOfDeadEntity instanceof Player) {
                    NBT.modify(itemInMainHand, nbt -> {
                        increasePlayerCounter(player, nbt, itemInMainHand);
                    });
                }
                if (typeOfDeadEntity instanceof EnderDragon) {
                    NBT.modify(itemInMainHand, nbt -> {
                        increaseDragonCounter(player, nbt, itemInMainHand);
                    });
                }
            }
        }
    }

    private static void increaseAnimalCounter(Player player, ReadWriteItemNBT nbt, ItemStack itemInMainHand) {
        if(nbt.hasTag("animalkills")) {
            nbt.setInteger("animalkills", nbt.getInteger("animalkills")+1);
            nbt.modifyMeta((readableNBT, meta) -> {
                List<Component> lore = meta.lore();
                if(lore == null) {
                    lore = new ArrayList<>();
                }
                // предыдущая
                TextComponent titleOfCounterPrevious = Component.text("Убийств животных");
                String serialize = PlainTextComponentSerializer.plainText().serialize(titleOfCounterPrevious);
                // обновляю новую
                Component unitOfCounter = Component.text(nbt.getInteger("animalkills"));
                Component titleOfCounter = Component.text("Убийств животных: ").append(unitOfCounter)
                        .color(NamedTextColor.DARK_RED);

                List<Component> list = lore.stream().map(counter ->{
                    String lineText = PlainTextComponentSerializer.plainText().serialize(counter);
                    if(lineText.contains(serialize)) {
                        return titleOfCounter;
                    }
                    return counter;
                }).toList();
                meta.lore(list);

                itemInMainHand.setItemMeta(meta);
                player.getInventory().setItemInMainHand(itemInMainHand);
            });
        }
    }
    private static void increasePlayerCounter(Player player, ReadWriteItemNBT nbt, ItemStack itemInMainHand) {
        if(nbt.hasTag("playerkills")) {
            nbt.setInteger("playerkills", nbt.getInteger("playerkills")+1);
            nbt.modifyMeta((readableNBT, meta) -> {
                List<Component> lore = meta.lore();
                if(lore == null) {
                    lore = new ArrayList<>();
                }

                TextComponent titleOfCounterPrevious = Component.text("Убийств игроков");
                String serialize = PlainTextComponentSerializer.plainText().serialize(titleOfCounterPrevious);

                Component unitOfCounter = Component.text(nbt.getInteger("playerkills"));
                Component titleOfCounter = Component.text("Убийств игроков: ").append(unitOfCounter)
                        .color(NamedTextColor.DARK_RED);

                List<Component> list = lore.stream().map(counter ->{
                    String lineText = PlainTextComponentSerializer.plainText().serialize(counter);
                    if(lineText.contains(serialize)) {
                        return titleOfCounter;
                    }
                    return counter;
                }).toList();
                meta.lore(list);

                itemInMainHand.setItemMeta(meta);
                player.getInventory().setItemInMainHand(itemInMainHand);
            });
        }
    }
    private static void increaseDragonCounter(Player player, ReadWriteItemNBT nbt, ItemStack itemInMainHand) {
        if(nbt.hasTag("dragonkills")) {
            nbt.setInteger("dragonkills", nbt.getInteger("dragonkills")+1);
            nbt.modifyMeta((readableNBT, meta) -> {
                List<Component> lore = meta.lore();
                if(lore == null) {
                    lore = new ArrayList<>();
                }

                TextComponent titleOfCounterPrevious = Component.text("Убийств драконов");
                String serialize = PlainTextComponentSerializer.plainText().serialize(titleOfCounterPrevious);

                Component unitOfCounter = Component.text(nbt.getInteger("dragonkills"));
                Component titleOfCounter = Component.text("Убийств драконов: ").append(unitOfCounter)
                        .color(NamedTextColor.DARK_RED);

                List<Component> list = lore.stream().map(counter ->{
                    String lineText = PlainTextComponentSerializer.plainText().serialize(counter);
                    if(lineText.contains(serialize)) {
                        return titleOfCounter;
                    }
                    return counter;
                }).toList();
                meta.lore(list);

                itemInMainHand.setItemMeta(meta);
                player.getInventory().setItemInMainHand(itemInMainHand);
            });
        }
    }
    private static void increaseMonsterCounter(Player player, ReadWriteItemNBT nbt, ItemStack itemInMainHand) {
        if(nbt.hasTag("monsterkills")) {
            nbt.setInteger("monsterkills", nbt.getInteger("monsterkills")+1);
            nbt.modifyMeta((readableNBT, meta) -> {
                List<Component> lore = meta.lore();
                if(lore == null) {
                    lore = new ArrayList<>();
                }

                TextComponent titleOfCounterPrevious = Component.text("Убийств монстров");
                String serialize = PlainTextComponentSerializer.plainText().serialize(titleOfCounterPrevious);

                Component unitOfCounter = Component.text(nbt.getInteger("monsterkills"));
                Component titleOfCounter = Component.text("Убийств монстров: ").append(unitOfCounter)
                        .color(NamedTextColor.DARK_RED);

                List<Component> list = lore.stream().map(counter ->{
                    String lineText = PlainTextComponentSerializer.plainText().serialize(counter);
                    if(lineText.contains(serialize)) {
                        return titleOfCounter;
                    }
                    return counter;
                }).toList();
                meta.lore(list);

                itemInMainHand.setItemMeta(meta);
                player.getInventory().setItemInMainHand(itemInMainHand);
            });
        }
    }
}
