package com.suraev.nbtPlugin.Command;

import com.suraev.nbtPlugin.Counter.CounterType;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NbtCounterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material type = itemInMainHand.getType();
            if (type != Material.AIR) {
                if (strings.length == 0) {
                    Component incorrectTypeOfCounter = Component.text("Укажи необходимый тип счетчика: ")
                            .color(NamedTextColor.DARK_RED)
                            .append(Component.text("dragon, player, animal, monster").color(NamedTextColor.AQUA));

                    player.sendMessage(incorrectTypeOfCounter);
                    return true;
                }

                String inputCounter = strings[0];
                String titleOfCounter = inputCounter + "kills";
                if (CounterType.isAllowed(inputCounter)) {


                    NBT.modify(itemInMainHand, nbt -> {
                        nbt.setInteger(titleOfCounter, 0);
                        nbt.modifyMeta(((readableNBT, meta) -> {
                            List<Component> lore = meta.lore();
                            if (lore == null) {
                                lore = new ArrayList<>();
                            }
                            TextComponent textForLore = switch (inputCounter.toLowerCase()) {
                                case "dragon" -> Component.text("Убийств драконов: 0");
                                case "monster" -> Component.text("Убийств монстров: 0");
                                case "animal" -> Component.text("Убийств животных: 0");
                                case "player" -> Component.text("Убйиств игроков: 0");
                                default ->
                                        throw new IllegalStateException("Unexpected value: " + inputCounter.toLowerCase());
                            };
                            lore.addFirst(textForLore);
                            meta.lore(lore);
                            itemInMainHand.setItemMeta(meta);
                            player.getInventory().setItemInMainHand(itemInMainHand);
                        }));
                    });
                    Component successMessage = Component.text("Успешно установлен счетчик убийств ").color(NamedTextColor.GREEN).append(Component.text(titleOfCounter).color(NamedTextColor.AQUA));
                    player.sendMessage(successMessage);
                    return true;
                }
                if (strings.length > 1) {
                    Component invalidArgument = Component.text("Недопустимое количество аргументов, доступен только один (наименование счетчика)")
                            .color(NamedTextColor.DARK_RED);
                    player.sendMessage(invalidArgument);
                }
                Component shouldItem = Component.text("В руках должен быть предмет").color(NamedTextColor.DARK_RED);
                player.sendMessage(shouldItem);
                return true;
            }
            Component invalidArgument = Component.text("Недопустимое количество аргументов, доступен только один (наименование счетчика)")
                    .color(NamedTextColor.DARK_RED);
            player.sendMessage(invalidArgument);
            return true;
        }
        return false;
    }
}
