package com.dragovorn.argonaut.api.util;

import com.dragovorn.argonaut.api.ArgonautAPI;
import org.bukkit.Bukkit;

public final class ConcurrencyUtil {

    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(ArgonautAPI.get(), runnable);
    }

    public static void syncLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLater(ArgonautAPI.get(), runnable, ticks);
    }

    public static void async(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(ArgonautAPI.get(), runnable);
    }
}
