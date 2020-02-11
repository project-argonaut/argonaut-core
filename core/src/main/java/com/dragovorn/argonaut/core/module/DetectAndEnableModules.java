package com.dragovorn.argonaut.core.module;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.event.server.ServerFinishLoadingEvent;
import com.dragovorn.argonaut.api.module.ArgonautModule;
import com.dragovorn.argonaut.api.module.IModule;
import com.dragovorn.argonaut.api.util.StringUtil;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public final class DetectAndEnableModules implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerLaunch(ServerFinishLoadingEvent event) {
        ArgonautAPI api = ArgonautAPI.get();

        api.info("Detecting modules...");

        // Enable our modules and make argonaut aware of them
        // TODO: Perform automatic module discovery
        List<IModule> modules = Lists.newLinkedList();

        // TODO: Find and add internal modules

        Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(p -> IModule.class.isAssignableFrom(p.getClass()))
                .filter(Plugin::isEnabled)
                .filter(p -> p.getClass().getAnnotation(ArgonautModule.class) != null)
                .map(p -> (IModule) p)
                .forEach(modules::add);

        modules.forEach(m -> ArgonautAPI.get().getModuleManager()
                .registerModule(m, m.getClass().getAnnotation(ArgonautModule.class)));

        int size = api.getModuleManager().getModules().size();

        api.info("Detected " + size + " " + StringUtil.getPlural("module", size) + "!");

        ArgonautAPI.get().getModuleManager().enableModules();
    }
}
