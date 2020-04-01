package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RecipeStatusEnumTest {

    private static final String DRAFT = "DRAFT";
    private static final String PUBLISHED = "PUBLISHED";
    private static final String DELETED = "DELETED";
    private static final String INVALID = "MEGAUPLOADED";

    @Test
    public void initFromValue() {
        RecipeStatusEnum recipeStatusEnumDraft = RecipeStatusEnum.fromValue(DRAFT);
        RecipeStatusEnum recipeStatusEnumPublished = RecipeStatusEnum.fromValue(PUBLISHED);
        RecipeStatusEnum recipeStatusEnumDeleted = RecipeStatusEnum.fromValue(DELETED);

        assertEquals(RecipeStatusEnum.DRAFT, recipeStatusEnumDraft);
        assertEquals(RecipeStatusEnum.PUBLISHED, recipeStatusEnumPublished);
        assertEquals(RecipeStatusEnum.DELETED, recipeStatusEnumDeleted);
    }

    @Test
    public void initFromInvalidValue() {
        RecipeStatusEnum recipeStatusEnum = RecipeStatusEnum.fromValue(INVALID);

        assertNull(recipeStatusEnum);
    }
}
