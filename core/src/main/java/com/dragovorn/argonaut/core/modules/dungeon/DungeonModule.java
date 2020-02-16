package com.dragovorn.argonaut.core.modules.dungeon;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.annotation.ArgonautModule;
import com.dragovorn.argonaut.api.module.AbstractArgonautModule;
import com.dragovorn.argonaut.core.modules.region.RegionModule;

@ArgonautModule(name = "dungeon", dependencies = RegionModule.class)
public final class DungeonModule extends AbstractArgonautModule {

    @Override
    public void onModuleEnable(ArgonautAPI api) {
//        DungeonModule.api.error("it worked!");
//        api.warn(regions.getTest());
    }

    @Override
    public void onModuleDisable(ArgonautAPI api) { }
}
