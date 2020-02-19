package com.ojodev.cookinghero.recipes.domain.constants;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;

public class RecipeConstants {

    private RecipeConstants() {

    }

    public static final LanguageEnumBO DEFAULT_LANGUAGE = LanguageEnumBO.EN;

     public static final String APPLICATION_JSON_PATCH = "application/json-patch+json";

    // TODO DMS: Ver donde centralizamos esto
    public static final String MEDIA_PHOTO_URI = "media/photos";

}
