package com.suraev.discord.Sheduler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

import java.util.Queue;

public class DiscordInformer implements Runnable{

    Queue<Component> queueOfMessage;

   public void prepare() {
       Component shopAndBalance = Component.text("Магазин сервера - ").color(NamedTextColor.DARK_AQUA).append(Component.text("/warp shop").color(NamedTextColor.GOLD))
               .appendNewline()
               .append(Component.text("Проверить баланс - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/balance").color(NamedTextColor.GOLD));
       Component joinToDiscordServer = Component.text("Присоединяйся к нашему Discord серверу:").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("https://discord.gg/GJtHtrkM").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://discord.gg/GJtHtrkM"));
       Component infoServer = Component.text("Для получения информации о сервере напиши:").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("/warp info").color(NamedTextColor.GOLD));
       Component farmMobs = Component.text("Пофармить мобов можно на:").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("/warp farm").color(NamedTextColor.GOLD));
       Component voteForServerOnHotMc = Component.text("Понравился сервер? Проголосуй:").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("https://hotmc.ru/vote-274393").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://hotmc.ru/vote-274393"));
       Component getListOfWarps = Component.text("Получить список варпов - ").color(NamedTextColor.DARK_AQUA).append(Component.text("/warp").color(NamedTextColor.GOLD));
       Component shareSuggestion = Component.text("Есть предложение? ").color(NamedTextColor.DARK_AQUA).append(Component.text("Поделись в /discord").color(NamedTextColor.GOLD));
       Component alfaTestMessage = Component.text("Сервер находится на стадии альфа тестирования.").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("Приносим изменения за возможные баги").color(NamedTextColor.DARK_AQUA));
       Component homeAboutMessage = Component.text("Поставить точку дома - ").color(NamedTextColor.DARK_AQUA).append(Component.text("/sethome").color(NamedTextColor.GOLD))
               .appendNewline()
               .append(Component.text("Попасть домой - ").color(NamedTextColor.DARK_AQUA)).append(Component.text("/home").color(NamedTextColor.GOLD));
       Component voteForServerOnMinecraftRating = Component.text("Понравился сервер? Проголосуй:").color(NamedTextColor.DARK_AQUA)
               .appendNewline()
               .append(Component.text("https://minecraftrating.ru/vote/283090/").color(NamedTextColor.GOLD))
               .clickEvent(ClickEvent.openUrl("https://minecraftrating.ru/vote/283090/"));
       queueOfMessage.add(shopAndBalance);
       queueOfMessage.add(joinToDiscordServer);
       queueOfMessage.add(infoServer);
       queueOfMessage.add(farmMobs);
       queueOfMessage.add(voteForServerOnHotMc);
       queueOfMessage.add(getListOfWarps);
       queueOfMessage.add(shareSuggestion);
       queueOfMessage.add(alfaTestMessage);
       queueOfMessage.add(homeAboutMessage);
       queueOfMessage.add(voteForServerOnMinecraftRating);
   }

    @Override
    public void run() {
        if(queueOfMessage.isEmpty()) {
            prepare();
        Component getMessage = queueOfMessage.remove();
        Bukkit.broadcast(getMessage);
    }
    }
}
