package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;

/**
 * Language type.
 */
public enum LanguageEnumBO {

    EN("en"),
    ES("es");

    private String value;

    LanguageEnumBO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static LanguageEnumBO fromValue(String text) {
        for (LanguageEnumBO b : LanguageEnumBO.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static String getValueList() {
        StringBuilder valueStringList= new StringBuilder();
        for (LanguageEnumBO language : LanguageEnumBO.values()) {
            valueStringList.append((valueStringList.length() > 0 ? ", " : "") + language.value);
        }
        return valueStringList.toString();
    }
}
