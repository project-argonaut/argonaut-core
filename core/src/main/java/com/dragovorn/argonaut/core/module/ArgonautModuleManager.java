package com.dragovorn.argonaut.core.module;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.module.AbstractArgonautModule;
import com.dragovorn.argonaut.api.module.ArgonautModule;
import com.dragovorn.argonaut.api.module.IArgonautModuleManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public final class ArgonautModuleManager implements IArgonautModuleManager {

    private final Map<Class<? extends AbstractArgonautModule>, AbstractArgonautModule> registeredModulesByClass = Maps.newHashMap();
    private final Map<String, AbstractArgonautModule> registeredModulesByString = Maps.newHashMap();

    private final LinkedList<AbstractArgonautModule> enableOrder = Lists.newLinkedList();

    @Override
    public void enableModules() {
        this.enableOrder.clear();

        Collection<AbstractArgonautModule> modules = this.registeredModulesByString.values();

        Stack<AbstractArgonautModule> layers = new Stack<>();

        modules.forEach(module -> this.addToEnableOrder(module, layers));

        ArgonautAPI api = ArgonautAPI.get();

        api.info("Enabling modules...");

        for (AbstractArgonautModule module : this.enableOrder) {
            ArgonautModule info = module.getModuleInfo();
            api.info("Enabling module: " + info.name() + " (v " + info.version() + ") by: " + Arrays.toString(info.authors()) + "...");
            try {
                module.onModuleEnable(ArgonautAPI.get());
                api.info("Enabled module: " + info.name() + "!");
            } catch (Throwable e) {
                api.error("Caught exception while enabling module " + info.name() + ":");
                this.enableOrder.remove(module);
                this.registeredModulesByClass.remove(module.getClass());
                this.registeredModulesByString.remove(info.name());
                e.printStackTrace();
            }
        }

        api.info("Successfully enabled " + this.enableOrder.size() + " modules!");
    }

    private void addToEnableOrder(AbstractArgonautModule module, Stack<AbstractArgonautModule> layers) {
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

        this.enableOrder.addFirst(module);
    }

    @Override
    public void disableModules() {
        ArrayList<AbstractArgonautModule> disableOrder = Lists.newArrayList(this.enableOrder);

        ArgonautAPI api = ArgonautAPI.get();

        api.info("Disabling " + disableOrder.size() + " modules...");

        for (int x = disableOrder.size() - 1; x >= 0; x--) {
            AbstractArgonautModule module = disableOrder.get(x);
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

        api.info("Disabled all modules!");
    }

    @Override
    public void registerModule(AbstractArgonautModule module, ArgonautModule moduleInfo) {
        module.setModuleInfo(moduleInfo);

        this.registeredModulesByClass.put(module.getClass(), module);
        this.registeredModulesByString.put(moduleInfo.name(), module);
    }

    @Override
    public AbstractArgonautModule getModule(Class<? extends AbstractArgonautModule> clazz) {
        return this.registeredModulesByClass.get(clazz);
    }

    @Override
    public AbstractArgonautModule getModule(String name) {
        return this.registeredModulesByString.get(name);
    }

    @Override
    public ImmutableList<AbstractArgonautModule> getModules() {
        return ImmutableList.copyOf(this.registeredModulesByClass.values());
    }
}
