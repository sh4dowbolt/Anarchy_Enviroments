package com.suraev.Event;

import com.suraev.Entity.ClanMember;

import java.util.concurrent.TimeUnit;

public class InviteClanRequest {

    private final String nameOfClan;
    private final ClanMember sender;
    private final ClanMember target;
    private final long expiryTime;

    public InviteClanRequest(String nameOfClan, ClanMember sender, ClanMember target, int duration, TimeUnit unit) {
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

    public ClanMember getSender() {
        return sender;
    }

    public ClanMember getTarget() {
        return target;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
