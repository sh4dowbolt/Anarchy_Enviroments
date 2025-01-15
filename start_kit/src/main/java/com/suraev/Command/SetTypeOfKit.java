package com.suraev.Command;

import com.suraev.Utils.Kit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class SetTypeOfKit implements CommandExecutor {
    private final FileConfiguration fileConfiguration;

    public SetTypeOfKit(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1) {
            String input = strings[0];
            if(Kit.isExisted(input)) {
                fileConfiguration.set("setting.type-kit", input);
            }
        }
        return false;
    }
}
