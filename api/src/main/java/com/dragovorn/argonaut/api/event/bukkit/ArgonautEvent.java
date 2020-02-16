package com.dragovorn.argonaut.api.event.bukkit;

import com.dragovorn.argonaut.api.ArgonautAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class ArgonautEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public ArgonautAPI getAPI() {
        return ArgonautAPI.get();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
