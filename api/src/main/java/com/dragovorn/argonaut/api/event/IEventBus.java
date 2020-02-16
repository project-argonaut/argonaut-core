package com.dragovorn.argonaut.api.event;

import org.bukkit.event.Event;

public interface IEventBus {

    <T> T registerListener(Class<T> clazz);

    void registerListener(Object object);

    void fire(Event event);
}
