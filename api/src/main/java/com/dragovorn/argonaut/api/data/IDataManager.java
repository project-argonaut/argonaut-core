package com.dragovorn.argonaut.api.data;

public interface IDataManager {

    void bindDataLoader();

    void registerDataLoader(Class<? extends IDataLoader> clazz);

    boolean hasDataLoader();

    IDataLoader getDataLoader();

    DataLoader getDataLoaderInfo();
}
