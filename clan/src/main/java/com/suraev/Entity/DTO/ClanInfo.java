package com.suraev.Entity.DTO;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
@Setter
public class ClanInfo {
    private Long id;
    private String title;

    public ClanInfo(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    

    public Component toComponent() {
        Component component = Component.text(id+", ").color(NamedTextColor.GOLD)
        .append(Component.text(title).color(NamedTextColor.AQUA));

        return component;
    }
}
