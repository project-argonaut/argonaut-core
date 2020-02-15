package com.dragovorn.argonaut.core;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.data.IDataManager;
import com.dragovorn.argonaut.api.event.server.ServerFinishLoadingEvent;
import com.dragovorn.argonaut.api.module.IArgonautModuleManager;
import com.dragovorn.argonaut.api.nms.INMSManager;
import com.dragovorn.argonaut.api.util.ConcurrencyUtil;
import com.dragovorn.argonaut.core.command.ArgonautCommandExecutor;
import com.dragovorn.argonaut.core.data.DataManager;
import com.dragovorn.argonaut.core.data.JSONDataLoader;
import com.dragovorn.argonaut.core.module.ArgonautModuleManager;
import com.dragovorn.argonaut.core.module.DetectAndEnableModules;
import com.dragovorn.argonaut.core.nms.NMSManager;
import com.dragovorn.argonaut.nms.v1_15_r1.NMSInterface1_15_r1;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public final class ArgonautCorePlugin extends ArgonautAPI {

    private final ArgonautModuleManager moduleManager = new ArgonautModuleManager();

    private final DataManager dataManager = new DataManager();

    private final NMSManager nmsManager = new NMSManager();

    @Override
    public void onLoad() {
        // This super onLoad call is needed because ArgonautAPI has some logic in onLoad that needs called
        super.onLoad();
    }

    @Override
    public void info(String message) {
        getLogger().info(message);
    }

    @Override
    public void warn(String message) {
        getLogger().warning(message);
    }

    @Override
    public void error(String message) {
        getLogger().severe(message);
    }

    @Override
    public void onEnable() {
        info("Hello, Spigot!");

        if (!this.getDataFolder().exists()) {
            info("Data folder doesn't exist! Creating it...");
            this.getDataFolder().mkdirs();
            this.saveDefaultConfig();
        }

        info("Registering pre-packaged NMS interfaces...");
        // Referencing the class like this might cause some ClassDefNotFound
        // errors, might need to move this to reflection detections.
        this.getNMSManager().registerNMSInterface(NMSInterface1_15_r1.class);

        info("Registering pre-packaged data loaders...");
        this.getDataManager().registerDataLoader(JSONDataLoader.class);

        info("Detected NMS Version: " + this.getNMSManager().getNMSVersion());
        info("Binding NMS interface...");
        this.getNMSManager().bindNMSInterface();

        info("Binding data loader...");
        this.getDataManager().bindDataLoader();

        // Ensure that the plugin has successfully registered its NMSInterface
        if (!this.getNMSManager().hasNMSInterface()) {
            // Shut down server if we haven't been able to bind to a NMSInterface
            warn("Unable to properly bind an NMS interface, this normally happens when the version");
            warn("is unsupported, please double check your spigot server version! Scheduling a shutdown...");

            ConcurrencyUtil.syncLater(Bukkit::shutdown, 1);
            return;
        }

        if (!this.getDataManager().hasDataLoader()) {
            // Shut down the server if we haven't been able to bind a data loader
            warn("Unable to properly bind a data loader, this normally happens when your config");
            warn("contains a data loader that is un supported, please double check your config");
            warn("file! Scheduling a shutdown...");

            ConcurrencyUtil.syncLater(Bukkit::shutdown, 1);
            return;
        }

        // 'Register' this to make sure the event is fired if the server successfully starts
        ConcurrencyUtil.syncLater(() -> Bukkit.getPluginManager().callEvent(new ServerFinishLoadingEvent()), 1);

        info("Bound data loader: " + this.getDataManager().getDataLoaderInfo().name() + "!");
        info("Bound NMS interface: " + this.getNMSManager().getNMSInterfaceInfo().name() + "!");

        info("Registering manual listeners...");
        register(new DetectAndEnableModules());

        // Register our command executor, which will handle the sub-command API for us.
        getCommand("argonaut").setExecutor(new ArgonautCommandExecutor());
    }

    @Override
    public void onDisable() {
        this.getModuleManager().disableModules();
    }

    @Override
    public IArgonautModuleManager getModuleManager() {
        return this.moduleManager;
    }

    @Override
    public INMSManager getNMSManager() {
        return this.nmsManager;
    }

    @Override
    public IDataManager getDataManager() {
        return this.dataManager;
    }

    private void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
