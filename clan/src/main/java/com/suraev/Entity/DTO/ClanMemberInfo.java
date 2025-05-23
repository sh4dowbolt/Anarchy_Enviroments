package com.suraev.Entity.DTO;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
@Setter
public class ClanMemberInfo {
    private String name;
    private String onlineStatus;
    private String role;


    public  Component toStringComponent() {
        Component component;
        if(onlineStatus.equals("Онлайн")) {
            component = Component.text(name)
            .append(Component.text(" - " + onlineStatus).color(NamedTextColor.GREEN));
        } else {
            component = Component.text(name)
            .append(Component.text(" - " + onlineStatus).color(NamedTextColor.RED));
        }
        return component;
    }
}
