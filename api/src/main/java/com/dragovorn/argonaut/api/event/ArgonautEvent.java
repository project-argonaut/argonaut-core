package com.dragovorn.argonaut.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class ArgonautEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
