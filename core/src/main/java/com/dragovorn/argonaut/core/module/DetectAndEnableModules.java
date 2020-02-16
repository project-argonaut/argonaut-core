package com.dragovorn.argonaut.core.module;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.annotation.ArgonautModule;
import com.dragovorn.argonaut.api.annotation.Event;
import com.dragovorn.argonaut.api.annotation.Executor;
import com.dragovorn.argonaut.api.event.bukkit.server.ServerLaunchEvent;
import com.dragovorn.argonaut.api.module.IModule;
import com.dragovorn.argonaut.api.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class DetectAndEnableModules implements Listener {

    @Executor(priority = EventPriority.LOWEST)
    public void onServerLaunch(@Event ServerLaunchEvent event) {
        ArgonautAPI api = event.getAPI();

        api.info("Detecting modules...");

        List<IModule> modules = Lists.newLinkedList();

        try {
            ClassPath.from(api.getClass().getClassLoader()).getTopLevelClasses().stream()
                    .filter(i -> api.getModuleManager().isPackage(i.getPackageName()))
                    .map(ClassPath.ClassInfo::load)
                    .filter(IModule.class::isAssignableFrom)
                    .filter(c -> c.isAnnotationPresent(ArgonautModule.class))
                    .map(aClass -> {
                        try {
                            return aClass.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(o -> (IModule) o)
                    .collect(Collectors.toCollection(() -> modules));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(p -> IModule.class.isAssignableFrom(p.getClass()))
                .filter(Plugin::isEnabled)
                .filter(p -> p.getClass().getAnnotation(ArgonautModule.class) != null)
                .map(p -> (IModule) p)
                .collect(Collectors.toCollection(() -> modules));

        modules.forEach(m -> ArgonautAPI.get().getModuleManager()
                .registerModule(m, m.getClass().getAnnotation(ArgonautModule.class)));

        int size = api.getModuleManager().getModules().size();

        api.info("Detected " + size + " " + StringUtil.getPlural("module", size) + "!");

        ArgonautAPI.get().getModuleManager().enableModules();
    }
}
