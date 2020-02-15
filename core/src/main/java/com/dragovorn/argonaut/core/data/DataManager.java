package com.dragovorn.argonaut.core.data;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.data.DataLoader;
import com.dragovorn.argonaut.api.data.IDataLoader;
import com.dragovorn.argonaut.api.data.IDataManager;
import com.google.common.collect.Maps;

import java.util.Map;

public final class DataManager implements IDataManager {

    private final Map<String, Class<? extends IDataLoader>> registeredDataLoaders = Maps.newHashMap();

    private final Map<Class<? extends IDataLoader>, DataLoader> dataLoaderMap = Maps.newHashMap();

    private IDataLoader dataLoader;

    @Override
    public void bindDataLoader() {
        String internal = ArgonautAPI.get().getConfig().getString("data-loader");

        if (!this.registeredDataLoaders.containsKey(internal)) {
            ArgonautAPI.get().error("Failed to find data loader " + internal + "!");
            return;
        }

        try {
            this.dataLoader = this.registeredDataLoaders.get(internal).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            ArgonautAPI.get().error("Failed to instantiate " + internal + " data loader!");
        }
    }

    @Override
    public void registerDataLoader(Class<? extends IDataLoader> clazz) {
        DataLoader dataLoaderInfo = clazz.getAnnotation(DataLoader.class);

        if (dataLoaderInfo == null) {
            throw new IllegalStateException(clazz.getCanonicalName() + " doesn't have a DataLoader annotation!");
        }

        if (this.registeredDataLoaders.containsKey(dataLoaderInfo.internal())) {
            throw new IllegalStateException("A data loader with internal name " + dataLoaderInfo.internal() + " already exists!");
        }

        this.registeredDataLoaders.put(dataLoaderInfo.internal(), clazz);
        this.dataLoaderMap.put(clazz, dataLoaderInfo);
    }

    @Override
    public boolean hasDataLoader() {
        return this.dataLoader != null;
    }

    @Override
    public IDataLoader getDataLoader() {
        return this.dataLoader;
    }

    @Override
    public DataLoader getDataLoaderInfo() {
        return this.dataLoaderMap.get(this.dataLoader.getClass());
    }
}
