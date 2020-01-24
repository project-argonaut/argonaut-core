package com.dragovorn.argonaut.core;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.event.server.ServerFinishLoadingEvent;
import com.dragovorn.argonaut.api.module.IArgonautModuleManager;
import com.dragovorn.argonaut.api.nms.INMSManager;
import com.dragovorn.argonaut.api.util.ConcurrencyUtil;
import com.dragovorn.argonaut.core.command.ArgonautCommandExecutor;
import com.dragovorn.argonaut.core.listener.ServerFinishLoadingListener;
import com.dragovorn.argonaut.core.module.ArgonautModuleManager;
import com.dragovorn.argonaut.core.nms.NMSManager;
import com.dragovorn.argonaut.nms.protocollib.ProtocolLibNMSInterface;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public final class ArgonautCorePlugin extends ArgonautAPI {

    private final ArgonautModuleManager moduleManager = new ArgonautModuleManager();

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
        ConcurrencyUtil.syncLater(() -> Bukkit.getPluginManager().callEvent(new ServerFinishLoadingEvent()), 1);

        info("Hello, Spigot!");

        info("Registering pre-packaged NMS interfaces...");
        this.getNMSManager().registerNMSInterface(ProtocolLibNMSInterface.class);

        info("Detected NMS Version: " + this.getNMSManager().getNMSVersion());
        info("Binding NMSInterface...");
        this.getNMSManager().bindNMSInterface();

        if (!this.getNMSManager().hasNMSInterface()) {
            // Shut down server if we haven't been able to bind to a NMSInterface
            warn("Unable to properly bind an NMSInterface, this normally happens when the version");
            warn("is unsupported, please double check your spigot server version, or install ProtocolLib");
            warn("Scheduling a shutdown...");

            ConcurrencyUtil.syncLater(Bukkit::shutdown, 1);
            return;
        }

        info("Bound NMSInterface: " + this.getNMSManager().getNMSInterfaceInfo().name() + " v" + this.getNMSManager().getNMSInterfaceInfo().version());

        info("Registering listeners...");
        register(new ServerFinishLoadingListener());

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

    private void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
