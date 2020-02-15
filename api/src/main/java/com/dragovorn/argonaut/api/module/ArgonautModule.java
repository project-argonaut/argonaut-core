package com.dragovorn.argonaut.api.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ArgonautModule {

    String name();

    String version() default "[DEFAULT]";

    String[] authors() default { };

    Class<? extends IModule>[] dependencies() default { };
}
