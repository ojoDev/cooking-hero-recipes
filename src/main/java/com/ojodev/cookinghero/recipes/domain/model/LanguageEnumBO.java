package com.ojodev.cookinghero.recipes.domain.model;

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
        String valueStringList = "";
        for (LanguageEnumBO language : LanguageEnumBO.values()) {
            valueStringList = valueStringList + (valueStringList.length() > 0 ? ", " : "") + language.value;
        }
        return valueStringList;
    }
}
