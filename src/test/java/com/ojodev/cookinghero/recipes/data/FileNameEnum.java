package com.ojodev.cookinghero.recipes.data;

public enum FileNameEnum {

    CUISINE_TYPE_INVALID_LANGUAGE("cuisine-type-invalid-language.json"),
    MEASURE_INVALID_LANGUAGE("measure-invalid-language.json"),
    MEASURE_PATCH_PARTIAL("measure-patch-partial");

    private String value;

    FileNameEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
