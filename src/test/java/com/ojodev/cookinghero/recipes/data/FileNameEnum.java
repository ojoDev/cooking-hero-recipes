package com.ojodev.cookinghero.recipes.data;

public enum FileNameEnum {

    CUISINE_TYPE_INVALID_LANGUAGE("cuisine-type-invalid-language.json"),
    MEASURE_INVALID_LANGUAGE("measure-invalid-language.json"),
    PRODUCT_INVALID_LANGUAGE("product-invalid-language.json"),
    MEASURE_PATCH_COMPLETE("measure-patch-complete.json"),
    MEASURE_PATCH_PARTIAL("measure-patch-partial.json"),
    MEASURE_PATCH_PARTIAL_AND_NULL("measure-patch-partial-null.json");

    private String value;

    FileNameEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
