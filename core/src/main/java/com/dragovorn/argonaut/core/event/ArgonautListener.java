package com.dragovorn.argonaut.core.event;

import org.bukkit.event.EventPriority;

import java.lang.reflect.Method;

public class ArgonautListener implements Comparable<ArgonautListener> {

    private final Object object;

    private final EventPriority priority;

    private final Method method;

    private final boolean async;

    ArgonautListener(Object object, EventPriority priority, Method method, boolean async) {
        this.object = object;
        this.priority = priority;
        this.method = method;
        this.async = async;
    }

    public Object getObject() {
        return this.object;
    }

    public EventPriority getPriority() {
        return this.priority;
    }

    public Method getMethod() {
        return this.method;
    }

    public boolean isAsync() {
        return this.async;
    }

    @Override
    public int compareTo(ArgonautListener argonautListener) {
        return 0; // TODO Event priorities lol
    }
}
