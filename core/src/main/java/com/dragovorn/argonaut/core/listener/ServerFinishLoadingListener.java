package com.dragovorn.argonaut.core.listener;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.event.server.ServerFinishLoadingEvent;
import com.dragovorn.argonaut.api.module.AbstractArgonautModule;
import com.dragovorn.argonaut.api.module.ArgonautModule;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public final class ServerFinishLoadingListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerLaunch(ServerFinishLoadingEvent event) {
        ArgonautAPI api = ArgonautAPI.get();

        api.info("Detecting modules...");

        // Enable our modules and make argonaut aware of them
        Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(p -> AbstractArgonautModule.class.isAssignableFrom(p.getClass()))
                .filter(Plugin::isEnabled)
                .filter(p -> p.getClass().getAnnotation(ArgonautModule.class) != null)
                .map(p -> (AbstractArgonautModule) p)
                .forEach(m -> ArgonautAPI.get().getModuleManager()
                        .registerModule(m, m.getClass().getAnnotation(ArgonautModule.class)));

        api.info("Detected " + api.getModuleManager().getModules().size() + " modules!");

        ArgonautAPI.get().getModuleManager().enableModules();
    }
}
