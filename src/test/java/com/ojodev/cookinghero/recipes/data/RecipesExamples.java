package com.ojodev.cookinghero.recipes.data;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.springframework.stereotype.Component;

@Component
public class RecipesExamples {
	

	public static final String RECIPE_01_ID = "0123456789ab0123456789ab";
	public static final String RECIPE_01_NAME = "Spanish omelet";
	public static final String RECIPE_01_DESCRIPTION = "Classic Spanish omelet filled with pan-fried potatoes and onion.";
	public static final Integer RECIPE_01_PREPARATION_TIME = 35;
	public static final Integer RECIPE_01_DIFFICULTY = 3;
	public static final String RECIPE_01_USER_ID = "ojodev";
	public static final LanguageEnumBO RECIPE_01_LANGUAGE = LanguageEnumBO.EN;

	public static final String RECIPE_ID_02 = "0123456789ab0123456789cd";
	public static final String RECIPE_NAME_02 = "Spaguetti";
	public static final String RECIPE_DESCRIPTION_02 = "Italian pasta with tomato";
	public static final String RECIPE_01_CUISINE_TYPE_01 = "spanish";
	public static final String RECIPE_01_CUISINE_TYPE_02 = "veggie";

	public static final String RECIPE_PHOTO_ID_01 = "21344123123";
	public static final String RECIPE_01_PHOTO_REF = "0123456789ab01234111111aa";
	public static final String RECIPE_USER_01 = "admin";
	public static final String RECIPE_CREATION_DATE_01_STRING = "2019-02-12T21:39:45.618Z";

	public static final String LANGUAGE_EN = "en";
	public static final String LANGUAGE_ES = "es";

}
