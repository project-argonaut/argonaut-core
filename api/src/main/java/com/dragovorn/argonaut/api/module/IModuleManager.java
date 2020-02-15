package com.dragovorn.argonaut.api.module;

import com.google.common.collect.ImmutableList;

public interface IModuleManager {

    void enableModules();

    void disableModules();

    void registerModulePackage(String packagePath);

    void registerModule(IModule module, ArgonautModule moduleInfo);

    boolean isRegistered(Class<? extends IModule> clazz);

    boolean isRegistered(String name);

    boolean isPackage(String packageName);

    IModule getModule(Class<? extends IModule> clazz);

    IModule getModule(String name);

    ImmutableList<IModule> getModules();

    ImmutableList<String> getModulePackages();
}
