package com.dragovorn.argonaut.api.module;

public abstract class AbstractArgonautModule implements IModule {

    private ArgonautModule moduleInfo;

    @Override
    public final void setModuleInfo(ArgonautModule moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public final ArgonautModule getModuleInfo() {
        return this.moduleInfo;
    }
}
