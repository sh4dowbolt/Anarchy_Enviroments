package com.suraev.Event;

import com.suraev.Entity.Clan;
import com.suraev.Entity.ClanMember;

import java.util.concurrent.TimeUnit;

public class InviteClanRequest {

    private final Clan clan;
    private final ClanMember sender;
    private final ClanMember target;
    private final long expiryTime;

    public InviteClanRequest(Clan clan, ClanMember sender, ClanMember target, int duration, TimeUnit unit) {
        this.clan = clan;
        this.sender = sender;
        this.target = target;
        this.expiryTime = System.currentTimeMillis()+unit.toMillis(duration);
    }

    public boolean isExpired() {
        return System.currentTimeMillis()>expiryTime;
    }

    public Clan getClan() {
        return clan;
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
