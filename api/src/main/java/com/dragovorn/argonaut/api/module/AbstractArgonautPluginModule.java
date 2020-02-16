package com.dragovorn.argonaut.api.module;

import com.dragovorn.argonaut.api.annotation.ArgonautModule;
import org.bukkit.plugin.java.JavaPlugin;

// TODO: Look into how implementing the Plugin interface can affect plugin loading.
public abstract class AbstractArgonautPluginModule extends JavaPlugin implements IModule {

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
