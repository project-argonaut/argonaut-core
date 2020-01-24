package com.dragovorn.argonaut.api.module;

public interface IArgonautModuleManager {

    void enableModules();

    void disableModules();

    void registerModule(AbstractArgonautModule module, ArgonautModule moduleInfo);

    AbstractArgonautModule getModule(Class<? extends AbstractArgonautModule> clazz);

    AbstractArgonautModule getModule(String name);
}
