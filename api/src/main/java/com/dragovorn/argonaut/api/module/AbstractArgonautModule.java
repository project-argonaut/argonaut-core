package com.dragovorn.argonaut.api.module;

import com.dragovorn.argonaut.api.ArgonautAPI;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractArgonautModule extends JavaPlugin {

    private ArgonautModule moduleInfo;

    public final void setModuleInfo(ArgonautModule moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public final ArgonautModule getModuleInfo() {
        return this.moduleInfo;
    }

    public abstract void onModuleEnable(ArgonautAPI api);
    public abstract void onModuleDisable(ArgonautAPI api);
}
