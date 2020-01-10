package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

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
        String valueStringList= "";
        for (LanguageEnum language : LanguageEnum.values()) {
            valueStringList = valueStringList + (valueStringList.length() > 0 ? ", " : "") + language.value;
        }
        return valueStringList;
    }
}
