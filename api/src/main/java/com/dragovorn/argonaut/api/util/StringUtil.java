package com.dragovorn.argonaut.api.util;

public final class StringUtil {

    public static String getPlural(String word, int amount) {
        return getPluralSuffix(word, "s", amount);
    }

    public static String getPluralSuffix(String word, String suffix, int amount) {
        return getPluralWord(word, word + suffix, amount);
    }

    public static String getPluralWord(String word, String plural, int amount) {
        return (amount > 1 || amount < -1 ? word : plural);
    }
}
