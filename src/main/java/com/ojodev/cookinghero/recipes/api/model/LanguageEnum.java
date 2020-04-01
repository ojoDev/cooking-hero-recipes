package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Language type.
 */
public enum LanguageEnum {
    EN("en"),
    ES("es");

    private String value;

    LanguageEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static LanguageEnum fromValue(String text) {
        for (LanguageEnum b : LanguageEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static String getValueList() {
        StringBuilder valueStringList= new StringBuilder();
        for (LanguageEnum language : LanguageEnum.values()) {
            valueStringList.append((valueStringList.length() > 0 ? ", " : "") + language.value);
        }
        return valueStringList.toString();
    }
}
