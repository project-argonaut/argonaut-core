package com.dragovorn.argonaut.api.event;

public class NMSInterfaceChangeEvent extends ArgonautEvent {

    private String nmsInterface;

    public NMSInterfaceChangeEvent(String nmsInterface) {
        this.nmsInterface = nmsInterface;
    }

    public String getNMSInterface() {
        return this.nmsInterface;
    }

    public void setNMSInterface(String nmsInterface) {
        this.nmsInterface = nmsInterface;
    }
}
