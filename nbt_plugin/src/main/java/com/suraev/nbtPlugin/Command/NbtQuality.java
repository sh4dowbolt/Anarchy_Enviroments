package com.suraev.nbtPlugin.Command;

import com.suraev.nbtPlugin.Counter.Quality;
import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
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

public class NbtQuality implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material type = itemInMainHand.getType();
            int length = strings.length;

            if (length == 0 || length > 1) {
                Component wrongArgument = Component.text("Ошибка! Недопустимое количество аргументов, необходим только один аргумент - quality")
                        .color(NamedTextColor.DARK_RED);
                player.sendMessage(wrongArgument);
                return true;
            }
            if (type != Material.AIR) {
                String inputQuality = strings[0];
                if (Quality.isAllowed(inputQuality)) {

                    TextComponent textForQuality = switch (inputQuality.toLowerCase()) {
                        case "uncommon"-> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("uncommon").color(NamedTextColor.GREEN));
                        case "rare" -> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("rare").color(NamedTextColor.BLUE));
                        case "legendary" -> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("legendary").color(NamedTextColor.LIGHT_PURPLE));
                        case "uniq" -> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("uniq").color(NamedTextColor.DARK_RED));
                        case "chaos" -> Component.text("").append(Component.text("К").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false))
                                .append(Component.text("a").style(Style.style(TextDecoration.OBFUSCATED)).color(NamedTextColor.WHITE))
                                .append(Component.text("чество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE))
                                .append(Component.text("ch")).color(NamedTextColor.GRAY)
                                .append(Component.text("a").style(Style.style(TextDecoration.OBFUSCATED)).color(NamedTextColor.GRAY))
                                .append((Component.text("os")).color(NamedTextColor.GRAY));
                        case "currency" -> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("currency").color(NamedTextColor.YELLOW));
                        case "jetton" -> Component.text("Качество: ").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE).append(Component.text("jetton").color(NamedTextColor.GOLD));
                        default -> throw new IllegalStateException("Unexpected value: " + inputQuality.toLowerCase());
                    };

                    NBT.modify(itemInMainHand, nbt -> {
                        nbt.modifyMeta((readableNBT, meta) -> {

                            List<Component> lore = meta.lore();
                            if(lore==null) {
                            lore = new ArrayList<>();
                            }

                            lore.addLast(textForQuality);
                            meta.lore(lore);

                            itemInMainHand.setItemMeta(meta);
                            player.getInventory().setItemInMainHand(itemInMainHand);
                        });
                    });
                    Component successMessage = Component.text("Предмету присовено качество").color(NamedTextColor.GREEN)
                            .appendSpace().append(Component.text(inputQuality).color(NamedTextColor.DARK_AQUA));
                    player.sendMessage(successMessage);
                    return true;
                }
                Component shouldItem = Component.text("Ошибка! Указали несуществующее качество. На данный момент доступны:").color(NamedTextColor.DARK_RED)
                        .appendSpace().append(Component.text("uncommon, rare, legendary, uniq, chaos, currency, jetton").color(NamedTextColor.DARK_AQUA));
                player.sendMessage(shouldItem);
                return true;
            }
            Component shouldItem = Component.text("В руках должен быть предмет").color(NamedTextColor.DARK_RED);
            player.sendMessage(shouldItem);
            return true;
        }
        return false;
    }
}
