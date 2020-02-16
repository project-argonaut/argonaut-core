package com.dragovorn.argonaut.core.module;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.annotation.ArgonautModule;
import com.dragovorn.argonaut.api.module.IModule;
import com.dragovorn.argonaut.api.module.IModuleManager;
import com.dragovorn.argonaut.api.util.StringUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public final class ModuleManager implements IModuleManager {

    private final Map<Class<? extends IModule>, IModule> registeredModulesByClass = Maps.newHashMap();

    private final Map<String, IModule> registeredModulesByString = Maps.newHashMap();

    private final LinkedList<IModule> enableOrder = Lists.newLinkedList();

    private final LinkedList<String> modulePaths = Lists.newLinkedList();

    @Override
    public void enableModules() {
        this.enableOrder.clear();

        Collection<IModule> modules = this.registeredModulesByString.values();

        Stack<IModule> layers = new Stack<>();

        modules.forEach(module -> this.addToEnableOrder(module, layers));

        String moduleStr = StringUtil.getPlural("module", this.enableOrder.size());

        ArgonautAPI api = ArgonautAPI.get();

        api.info("Enabling " + moduleStr + "...");

        for (IModule module : this.enableOrder) {
            ArgonautModule info = module.getModuleInfo();

            String version = ArgonautAPI.get().getDescription().getVersion();
            if (!info.version().equals("[DEFAULT]")) {
                version = info.version();
            }

            if (info.authors().length == 0) {
                api.info("Enabling module: " + info.name() + " (v" + version + ")...");
            } else {
                api.info("Enabling module: " + info.name() + " (v" + version + ") by: " + Arrays.toString(info.authors()) + "...");
            }

            try {
                module.onModuleEnable(ArgonautAPI.get());
                api.info("Enabled module: " + info.name() + "!");
            } catch (Throwable e) {
                api.error("Caught exception while enabling module " + info.name() + ":");
                this.enableOrder.remove(module);
                this.registeredModulesByClass.remove(module.getClass());
                this.registeredModulesByString.remove(info.name());
                e.printStackTrace();
                api.error("Removing " + info.name() + " from registered modules!");
            }
        }

        api.info("Successfully enabled " + this.enableOrder.size() + " " + moduleStr + "!");
    }

    private void addToEnableOrder(IModule module, Stack<IModule> layers) {
        if (layers.contains(module)) {
            throw new RuntimeException("Circular dependency found! " + module.getModuleInfo().name() + " is a circular dependency!");
        }

        layers.add(module);

        Arrays.stream(module.getModuleInfo().dependencies())
                .forEach(dep -> this.addToEnableOrder(this.getModule(dep), layers));

        layers.pop();

        if (this.enableOrder.contains(module)) {
            return;
        }

        this.enableOrder.add(module);
    }

    @Override
    public void disableModules() {
        ArrayList<IModule> disableOrder = Lists.newArrayList(this.enableOrder);

        ArgonautAPI api = ArgonautAPI.get();

        String modules = StringUtil.getPlural("module", disableOrder.size());

        api.info("Disabling " + disableOrder.size() + " " + modules + "...");

        for (int x = disableOrder.size() - 1; x >= 0; x--) {
            IModule module = disableOrder.get(x);
            ArgonautModule info = module.getModuleInfo();

            api.info("Disabling " + info.name() + "...");

            try {
                module.onModuleDisable(ArgonautAPI.get());
                api.info("Disabled " + info.name() + "!");
            } catch (Throwable e) {
                api.error("Caught exception when disabling module: " + info.name() + "!");
                e.printStackTrace();
            }
        }

        api.info("Disabled " + disableOrder.size() + " " + modules + "!");
    }

    @Override
    public void registerModulePackage(String packagePath) {
        if (this.modulePaths.contains(packagePath)) {
            return;
        }

        this.modulePaths.add(packagePath);
    }

    @Override
    public void registerModule(IModule module, ArgonautModule moduleInfo) {
        module.setModuleInfo(moduleInfo);

        this.registeredModulesByClass.put(module.getClass(), module);
        this.registeredModulesByString.put(moduleInfo.name(), module);
    }

    @Override
    public boolean isRegistered(Class<? extends IModule> clazz) {
        return this.registeredModulesByClass.containsKey(clazz);
    }

    @Override
    public boolean isRegistered(String name) {
        return this.registeredModulesByString.containsKey(name);
    }

    @Override
    public boolean isPackage(String packageName) {
        for (String path : this.modulePaths) {
            if (packageName.startsWith(path)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IModule getModule(Class<? extends IModule> clazz) {
        return this.registeredModulesByClass.get(clazz);
    }

    @Override
    public IModule getModule(String name) {
        return this.registeredModulesByString.get(name);
    }

    @Override
    public ImmutableList<IModule> getModules() {
        return ImmutableList.copyOf(this.registeredModulesByClass.values());
    }

    @Override
    public ImmutableList<String> getModulePackages() {
        return ImmutableList.copyOf(this.modulePaths);
    }
}
