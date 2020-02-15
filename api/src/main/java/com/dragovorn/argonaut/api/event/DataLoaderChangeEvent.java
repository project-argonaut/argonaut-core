package com.dragovorn.argonaut.api.event;

public class DataLoaderChangeEvent extends ArgonautEvent {

    private String dataLoader;

    public DataLoaderChangeEvent(String dataLoader) {
        this.dataLoader = dataLoader;
    }

    public String getDataLoader() {
        return this.dataLoader;
    }

    public void setDataLoader(String dataLoader) {
        this.dataLoader = dataLoader;
    }
}
