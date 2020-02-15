package com.dragovorn.argonaut.api;

import com.dragovorn.argonaut.api.data.IDataManager;
import com.dragovorn.argonaut.api.module.IModuleManager;
import com.dragovorn.argonaut.api.nms.INMSManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ArgonautAPI extends JavaPlugin {

    private static ArgonautAPI instance;

    public static ArgonautAPI get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public abstract void info(String message);

    public abstract void warn(String message);

    public abstract void error(String message);

    public abstract IModuleManager getModuleManager();

    public abstract INMSManager getNMSManager();

    public abstract IDataManager getDataManager();
}
