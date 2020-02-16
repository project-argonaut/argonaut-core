package com.dragovorn.argonaut.core.modules.region;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.module.AbstractArgonautModule;
import com.dragovorn.argonaut.api.annotation.ArgonautModule;

@ArgonautModule(name = "region")
public final class RegionModule extends AbstractArgonautModule {

    @Override
    public void onModuleEnable(ArgonautAPI api) { }

    @Override
    public void onModuleDisable(ArgonautAPI api) { }

    public String getTest() {
        return "TEST";
    }
}
