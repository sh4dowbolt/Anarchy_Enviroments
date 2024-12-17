package com.suraev.nbtPlugin.Command;

import com.suraev.nbtPlugin.Command.util.CustomEnchantmentHandler;
import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.Range;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NbtEnchantment implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            if (strings.length > 2 || strings.length == 0) {
                Component invalidArgument = Component.text("Недопустимое количество аргументов, введи от 1 до 2 (наименование зачарования, уровень)")
                        .color(NamedTextColor.DARK_RED);
                player.sendMessage(invalidArgument);
                return true;
            }
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material type = itemInMainHand.getType();

            if(type!= Material.AIR) {

                String inputEnchantment = strings[0].toLowerCase();

                if (strings.length == 1) {
                    if (isCustomEnchantment(inputEnchantment)) {
                        if (inputEnchantment.equalsIgnoreCase("unbreakable")) {
                            NBT.modifyComponents(itemInMainHand, nbt -> {
                                nbt.getOrCreateCompound("unbreakable").setBoolean("unbreakable", true);

                            });
                            //ItemStack unbreakable = Unbreakable.handle(itemInMainHand);
                            player.getInventory().setItemInMainHand(itemInMainHand);

                            Component successMessage = Component.text("Успешно добавлено кастомное зачарование ").color(NamedTextColor.GREEN).append(Component.text(inputEnchantment).color(NamedTextColor.AQUA));

                            player.sendMessage(successMessage);
                            return true;
                        }
                        Component ifNotItemMessage = Component.text("Указано несуществующее кастомное зачарование, см. докуметацию").color(NamedTextColor.DARK_RED);
                        player.sendMessage(ifNotItemMessage);
                        return true;
                    }
                    if (isAllowedEnchantment(inputEnchantment)) {
                        Component notLvlCustomEnchantment = Component.text("Для стандартного зачарования необходимо указать уровень").color(NamedTextColor.DARK_RED);
                        player.sendMessage(notLvlCustomEnchantment);
                        return true;
                    }
                    Component notLvlCustomEnchantment = Component.text("Ошибка! Вы ввели несуществующее зачарование! См. документацию").color(NamedTextColor.DARK_RED);
                    player.sendMessage(notLvlCustomEnchantment);
                    return true;

                }
                if (strings.length == 2) {
                    int lvlOfEnchantment;
                    try {
                        lvlOfEnchantment = Integer.parseInt((strings[1]));

                    } catch (NumberFormatException exception) {
                        Component invalidLevelOfEnch = Component.text("Введите уровень зачарования в виде цифр. Символы, буквы игнорируются")
                                .color(NamedTextColor.DARK_RED);
                        player.sendMessage(invalidLevelOfEnch);
                        return true;
                    }

                    if (isAllowedEnchantment(inputEnchantment)) {

                        Range<Integer> between = Range.between(1, 200);

                        if (between.contains(lvlOfEnchantment)) {
                            NamespacedKey nameForEnchantment = NamespacedKey.fromString("minecraft:" + inputEnchantment);
                            Enchantment enchantmentSet = Enchantment.getByKey(nameForEnchantment);
                            NBT.modify(itemInMainHand, nbt -> {
                                nbt.getOrCreateCompound("Enchantments").setInteger(enchantmentSet.getKey().getKey(), lvlOfEnchantment);
                            });

                            itemInMainHand.addUnsafeEnchantment(enchantmentSet, lvlOfEnchantment);

                            Component successMessage = Component.text("Успешно добавлено зачарование: ")
                                    .color(NamedTextColor.GREEN)
                                    .append(Component.text(inputEnchantment).color(NamedTextColor.AQUA))
                                    .appendSpace()
                                    .append(Component.text(lvlOfEnchantment).color(NamedTextColor.AQUA))
                                    .appendSpace().append(Component.text("lvl").color(NamedTextColor.AQUA));
                            player.sendMessage(successMessage);
                            return true;
                        }
                        Component invalidLvlOfEnch = Component.text("Ошибка! Ввели неправильный уровень для зачарования. Разрешенный диапазон от 1 до 200 уровня").color(NamedTextColor.DARK_RED);
                        player.sendMessage(invalidLvlOfEnch);
                        return true;
                    }
                    Component onlyEnchTitle = Component.text("Ошибка! Ввели неправильно наименование зачарование! См. документацию").color(NamedTextColor.DARK_RED);
                    player.sendMessage(onlyEnchTitle);
                    return true;
                }
                Component airInHand = Component.text("Ошибка! В руках должен быть предмет").color(NamedTextColor.DARK_RED);
                player.sendMessage(airInHand);
                return true;
            }
            Component ifNotItemMessage = Component.text("Воспользовать данной командой может только игрок").color(NamedTextColor.DARK_RED);
            player.sendMessage(ifNotItemMessage);
            return true;
        }
        return false;
    }

    private static boolean isAllowedEnchantment(String inputEnchantment) {
        for(Enchantment value : Enchantment.values()) {
          if(value.getKey().toString().contains(inputEnchantment)) {
              return true;
          }
        }
        return false;
    }
    private static boolean isCustomEnchantment(String inputEnchantment) {
        for(String string: CustomEnchantmentHandler.values()) {
            if(string.contains(inputEnchantment)){
                return true;
            }
        }
        return false;
    }


}
