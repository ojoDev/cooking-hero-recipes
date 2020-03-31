package com.ojodev.cookinghero.recipes.domain.enume;

/**
 * Recipe status.   DRAFT is for no published recipes, may have inconsistencies. PUBLISHED is for published and public recipes. DELETED is for deleted users or recipes.
 */
public enum RecipeStatusEnumBO {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    DELETED("DELETED");

    private String value;

    RecipeStatusEnumBO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
