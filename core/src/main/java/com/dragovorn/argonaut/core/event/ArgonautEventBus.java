package com.dragovorn.argonaut.core.event;

import com.dragovorn.argonaut.api.event.IEventBus;
import com.google.common.collect.Maps;
import org.bukkit.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public final class ArgonautEventBus implements IEventBus {

    private final Map<Class<? extends Event>, List<ArgonautListener>> listeners = Maps.newConcurrentMap();

    @Override
    public <T> T registerListener(Class<T> clazz) {
        return null;
    }

    @Override
    public void registerListener(Object object) {

    }

    @Override
    public void fire(Event event) {
        if (!this.listeners.containsKey(event.getClass())) {
            return;
        }

        List<ArgonautListener> listeners = this.listeners.get(event.getClass());

        listeners.forEach(l -> {
            try {
                l.getMethod().invoke(l.getObject(), getParameters(l.getMethod(), event));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private Object[] getParameters(Method method, Event event) {
        return new Object[0];
    }
}
