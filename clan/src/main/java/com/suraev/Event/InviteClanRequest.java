package com.suraev.Event;

import com.suraev.Entity.DTO.PlayerDTO;

import java.util.concurrent.TimeUnit;

public class InviteClanRequest {

    private final String nameOfClan;
    private final PlayerDTO sender;
    private final PlayerDTO target;
    private final long expiryTime;

    public InviteClanRequest(String nameOfClan, PlayerDTO sender, PlayerDTO target, int duration, TimeUnit unit) {
        this.nameOfClan = nameOfClan;
        this.sender = sender;
        this.target = target;
        this.expiryTime = System.currentTimeMillis()+unit.toMillis(duration);
    }

    public boolean isExpired() {
        return System.currentTimeMillis()>expiryTime;
    }

    public String getNameOfClan() {
        return nameOfClan;
    }

    public PlayerDTO getSender() {
        return sender;
    }

    public PlayerDTO getTarget() {
        return target;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
