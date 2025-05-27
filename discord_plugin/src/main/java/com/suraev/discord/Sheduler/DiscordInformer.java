package com.suraev.discord.Sheduler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;


import java.util.ArrayDeque;
import java.util.Queue;

public class DiscordInformer implements Runnable{

    private final Queue<Component> queueOfMessage;

    public DiscordInformer() {
        queueOfMessage = new ArrayDeque<>();
        prepare();
    }

    private void prepare() {
        Component prefixForMessage = Component.text("[").color(NamedTextColor.GREEN).append(Component.text("SERVER").color(NamedTextColor.LIGHT_PURPLE)).append(Component.text("]: ").color(NamedTextColor.GREEN));

        Component shopAndBalance = prefixForMessage.append(Component.text("Магазин сервера - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/warp shop").color(NamedTextColor.GOLD))
               .appendNewline()
               .append(Component.text("Проверить баланс - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/balance").color(NamedTextColor.GOLD));
        Component joinToDiscordServer = prefixForMessage.append(Component.text("Присоединяйся к нашему Discord серверу:").color(NamedTextColor.DARK_AQUA))
               .appendNewline()
               .append(Component.text("https://discord.gg/GJtHtrkM").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://discord.gg/GJtHtrkM"));
        Component farmMobs = prefixForMessage.append(Component.text("Пофармить мобов можно на:").color(NamedTextColor.DARK_AQUA))
               .appendNewline()
               .append(Component.text("/warp farm").color(NamedTextColor.GOLD));
        Component voteForServerOnHotMc = prefixForMessage.append(Component.text("Понравился сервер? Проголосуй:").color(NamedTextColor.DARK_AQUA))
               .appendNewline()
               .append(Component.text("https://hotmc.ru/vote-274393").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://hotmc.ru/vote-274393"));
        Component getListOfWarps = prefixForMessage.append(Component.text("Получить список варпов - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/warps").color(NamedTextColor.GOLD));
        Component shareSuggestion = prefixForMessage.append(Component.text("Есть предложение? ").color(NamedTextColor.DARK_AQUA)).append(Component.text("Поделись в /discord").color(NamedTextColor.GOLD));
   
        Component pvpArena = prefixForMessage.append(Component.text("Хочешь ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("PvP? ").color(NamedTextColor.RED))
                .append(Component.text("Иди на ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("/warp arena").color(NamedTextColor.GOLD))
                .append(Component.text(" и пиши ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("/warp kit").color(NamedTextColor.GOLD));
        Component farmLoot = prefixForMessage.append(Component.text("Пофармить ценный лут можно на ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("/warp mobs").color(NamedTextColor.GOLD));
        Component spleefArena = prefixForMessage.append(Component.text("На сервере есть  ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("сплиф-арена. ").color(NamedTextColor.WHITE))
                .append(Component.text("Пиши  ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("/warp spleef").color(NamedTextColor.GOLD));
        Component homeAboutMessage = prefixForMessage.append(Component.text("Поставить точку дома - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/sethome").color(NamedTextColor.GOLD))
               .appendNewline()
               .append(Component.text("Попасть домой - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/home").color(NamedTextColor.GOLD));
        Component voteForServerOnMinecraftRating = prefixForMessage.append(Component.text("Понравился сервер? Проголосуй:").color(NamedTextColor.DARK_AQUA))
               .appendNewline()
               .append(Component.text("https://minecraftrating.ru/vote/283090/").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://minecraftrating.ru/vote/283090/"));
        Component kitSpin = prefixForMessage.append(Component.text("Пиши ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("/kit spin ").color(NamedTextColor.GOLD))
                .append(Component.text("каждый час").color(NamedTextColor.DARK_AQUA));
        Component dropEgg = prefixForMessage.append(Component.text("Шанс выпадения яйца дракона - ").color(NamedTextColor.DARK_AQUA))
                .append(Component.text("7%").color(NamedTextColor.GOLD));

       queueOfMessage.add(shopAndBalance);
       queueOfMessage.add(joinToDiscordServer);
       queueOfMessage.add(farmMobs);
       queueOfMessage.add(voteForServerOnHotMc);
       queueOfMessage.add(getListOfWarps);
       queueOfMessage.add(shareSuggestion);
       queueOfMessage.add(pvpArena);
       queueOfMessage.add(homeAboutMessage);
       queueOfMessage.add(voteForServerOnMinecraftRating);
       queueOfMessage.add(kitSpin);
       queueOfMessage.add(dropEgg);
       queueOfMessage.add(farmLoot);
       queueOfMessage.add(spleefArena);
   }

    @Override
    public void run() {
        Component first = queueOfMessage.remove();
        Bukkit.broadcast(first);
        queueOfMessage.offer(first);
    }

}
