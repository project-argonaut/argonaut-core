package com.dragovorn.argonaut.api.nms;

import com.dragovorn.argonaut.api.annotation.NMSInterface;

public interface INMSManager {

    void bindNMSInterface();

    void registerNMSInterface(Class<? extends INMSInterface> nmsInterface);

    boolean hasNMSInterface();

    String getNMSVersion();

    INMSInterface getNMSInterface();

    NMSInterface getNMSInterfaceInfo();
}
