package com.suraev.Entity;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
@Setter
public class ClanMember {

    private String name;
    private UUID uuid;
    private Role role;

  
    public ClanMember(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.role = Role.MEMBER;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClanMember that = (ClanMember) object;
        return Objects.equals(name, that.name) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uuid);
    }

    public boolean isOnline() {
        return Bukkit.getPlayer(uuid) != null;
    }

    public  Component toStringComponent() {
        Component component;
        if(isOnline()) {
            component = Component.text(name)
            .append(Component.text(" - " + "Онлайн").color(NamedTextColor.GREEN));
        } else {
            component = Component.text(name)
            .append(Component.text(" - " + "Офлайн").color(NamedTextColor.RED));
        }
        return component;
    }
}
