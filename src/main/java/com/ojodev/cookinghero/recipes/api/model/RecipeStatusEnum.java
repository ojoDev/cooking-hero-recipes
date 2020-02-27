package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Recipe status.   DRAFT is for no published recipes, may have inconsistences. PUBLISHED is for publisded and public recipes. DELETED is for deleted users or recipes.
 */
public enum RecipeStatusEnum {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    DELETED("DELETED");

    private String value;

    RecipeStatusEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static RecipeStatusEnum fromValue(String text) {
        for (RecipeStatusEnum b : RecipeStatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
