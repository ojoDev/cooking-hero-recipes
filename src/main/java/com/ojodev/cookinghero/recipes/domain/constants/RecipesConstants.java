package com.ojodev.cookinghero.recipes.domain.constants;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;

public class RecipesConstants {

    private RecipesConstants() {

    }

    public static final LanguageEnumBO DEFAULT_LANGUAGE = LanguageEnumBO.EN;

    public static final String ACCEPT_LANGUAGE_SEPARATOR = ",";

    // TODO DMS: Ver donde centralizamos esto
    public static final String MEDIA_PHOTO_URI = "media/photos";

}
