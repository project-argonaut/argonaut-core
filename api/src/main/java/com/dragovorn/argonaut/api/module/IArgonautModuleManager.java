package com.dragovorn.argonaut.api.module;

import com.google.common.collect.ImmutableCollection;

public interface IArgonautModuleManager {

    void enableModules();

    void disableModules();

    void registerModule(AbstractArgonautModule module, ArgonautModule moduleInfo);

    AbstractArgonautModule getModule(Class<? extends AbstractArgonautModule> clazz);

    AbstractArgonautModule getModule(String name);

    ImmutableCollection<AbstractArgonautModule> getModules();
}
