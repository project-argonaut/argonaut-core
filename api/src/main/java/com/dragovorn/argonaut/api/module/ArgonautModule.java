package com.dragovorn.argonaut.api.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ArgonautModule {

    String name();

    String version();

    String[] authors();

    Class<? extends AbstractArgonautModule>[] dependencies();
}
