package com.ojodev.cookinghero.recipes.data;

public enum FileNameEnum {

    CUISINE_TYPE_INVALID_LANGUAGE("cuisine-type-invalid-language.json"),
    INGREDIENT_PATCH_COMPLETE("ingredient-patch-complete.json"),
    INGREDIENT_PATCH_PARTIAL("ingredient-patch-partial.json"),
    INGREDIENT_PATCH_PARTIAL_NULL("ingredient-patch-partial-null.json"),
    MEASURE_INVALID_LANGUAGE("measure-invalid-language.json"),
    MEASURE_PATCH_COMPLETE("measure-patch-complete.json"),
    MEASURE_PATCH_PARTIAL("measure-patch-partial.json"),
    MEASURE_PATCH_PARTIAL_AND_NULL("measure-patch-partial-null.json"),
    PRODUCT_PATCH_COMPLETE("product-patch-complete.json"),
    PRODUCT_INVALID_LANGUAGE("product-invalid-language.json"),
    PRODUCT_PATCH_PARTIAL("product-patch-partial.json"),
    PRODUCT_PATCH_PARTIAL_AND_NULL("product-patch-partial-null.json"),
    RECIPE_POST_NO_MANDATORY_VALUES("recipe-post-no-mandatory-values.json");


    private String value;

    FileNameEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
