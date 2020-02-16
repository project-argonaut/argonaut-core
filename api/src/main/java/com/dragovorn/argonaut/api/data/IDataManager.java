package com.dragovorn.argonaut.api.data;

import com.dragovorn.argonaut.api.annotation.DataLoader;

public interface IDataManager {

    void bindDataLoader();

    void registerDataLoader(Class<? extends IDataLoader> clazz);

    boolean hasDataLoader();

    IDataLoader getDataLoader();

    DataLoader getDataLoaderInfo();
}
