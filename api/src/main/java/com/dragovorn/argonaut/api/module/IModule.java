package com.dragovorn.argonaut.api.module;

import com.dragovorn.argonaut.api.ArgonautAPI;

public interface IModule {

    void onModuleEnable(ArgonautAPI api);

    void onModuleDisable(ArgonautAPI api);

    void setModuleInfo(ArgonautModule moduleInfo);

    ArgonautModule getModuleInfo();
}
