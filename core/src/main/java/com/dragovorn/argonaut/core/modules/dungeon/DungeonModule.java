package com.dragovorn.argonaut.core.modules.dungeon;

import com.dragovorn.argonaut.api.ArgonautAPI;
import com.dragovorn.argonaut.api.module.AbstractArgonautModule;
import com.dragovorn.argonaut.api.module.ArgonautModule;
import com.dragovorn.argonaut.core.modules.region.RegionModule;

@ArgonautModule(name = "dungeon", dependencies = RegionModule.class)
public final class DungeonModule extends AbstractArgonautModule {

    @Override
    public void onModuleEnable(ArgonautAPI api) { }

    @Override
    public void onModuleDisable(ArgonautAPI api) { }
}
