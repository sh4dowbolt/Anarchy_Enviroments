package com.suraev.nbtPlugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEmptySpace  implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInEmptySpace(PlayerMoveEvent event) {
        Player player = event.getPlayer();


        Location location = player.getLocation();
        World world = location.getWorld();

        if(world != null) {
            String worldName=world.getName();
            double locY=location.getY();

            if((worldName.equalsIgnoreCase("world") && locY <=-65) ||
            (worldName.equalsIgnoreCase("world_nether")) && (locY <= -1 | locY>=128)) {
                tpPlayerToCorrectPosition(player);
            }
        }
    }

    private void tpPlayerToCorrectPosition(Player player) {
        final Location locationToTP = new Location(Bukkit.getServer().getWorlds().get(0), 0, 67, 0);
        player.teleport(locationToTP);
    }

}
