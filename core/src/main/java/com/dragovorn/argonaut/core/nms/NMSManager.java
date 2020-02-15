package com.dragovorn.argonaut.core.nms;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.event.NMSInterfaceChangeEvent;
import com.dragovorn.argonaut.api.nms.INMSInterface;
import com.dragovorn.argonaut.api.nms.INMSManager;
import com.dragovorn.argonaut.api.nms.NMSInterface;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;

import java.util.Map;

public final class NMSManager implements INMSManager {

    private final Map<String, Class<? extends INMSInterface>> registeredNMSInterfaces = Maps.newHashMap();

    private final Map<Class<? extends INMSInterface>, NMSInterface> nmsInterfaceInfo = Maps.newHashMap();

    private final String nmsVersion = Bukkit.getServer().getClass().getPackage().getName().substring(
            Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);

    private INMSInterface nmsInterface;

    @Override
    public void bindNMSInterface() {
        useNMSInterface(this.nmsVersion.toLowerCase());
    }

    @Override
    public void registerNMSInterface(Class<? extends INMSInterface> nmsInterface) {
        NMSInterface interfaceInfo = nmsInterface.getAnnotation(NMSInterface.class);

        if (interfaceInfo == null) {
            throw new IllegalStateException(nmsInterface.getCanonicalName() + " doesn't have a NMSInterface annotation.");
        }

        if (this.registeredNMSInterfaces.containsKey(interfaceInfo.internal())) {
            throw new IllegalStateException("An NMS interface with internal name " + interfaceInfo.internal() + " exists!");
        }

        this.registeredNMSInterfaces.put(interfaceInfo.internal(), nmsInterface);
        this.nmsInterfaceInfo.put(nmsInterface, interfaceInfo);
    }

    @Override
    public boolean hasNMSInterface() {
        return this.getNMSInterface() != null;
    }

    @Override
    public String getNMSVersion() {
        return this.nmsVersion;
    }

    @Override
    public INMSInterface getNMSInterface() {
        return this.nmsInterface;
    }

    @Override
    public NMSInterface getNMSInterfaceInfo() {
        return this.nmsInterfaceInfo.get(this.nmsInterface.getClass());
    }

    private void useNMSInterface(String internalName) {
        if (this.nmsInterface != null) {
            throw new IllegalStateException("Cannot change nmsInterface after it has been initialized!");
        }

        NMSInterfaceChangeEvent event = new NMSInterfaceChangeEvent(internalName);
        Bukkit.getPluginManager().callEvent(event);

        if (!this.registeredNMSInterfaces.containsKey(event.getNMSInterface())) {
            ArgonautAPI.get().error("Failed to find NMSInterface: " + event.getNMSInterface());
            return;
        }

        this.nmsInterface = instantiateNMSInterface(event.getNMSInterface());
    }

    private INMSInterface instantiateNMSInterface(String key) {
        try {
            return this.registeredNMSInterfaces.get(key).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
