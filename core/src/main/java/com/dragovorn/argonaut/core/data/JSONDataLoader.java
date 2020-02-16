package com.dragovorn.argonaut.core.data;

import com.dragovorn.argonaut.api.annotation.DataLoader;
import com.dragovorn.argonaut.api.data.IDataLoader;

@DataLoader(name = "JSON", internal = "json")
public final class JSONDataLoader implements IDataLoader {
}
