package com.suraev.Entity;

import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;


public class ClanMember {

    private String name;
    private UUID uuid;
    private Role role;

  
    public ClanMember(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.role = Role.MEMBER;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }
    
    public Role getRole() {
        return role;
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
}
