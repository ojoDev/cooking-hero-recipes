package com.ojodev.cookinghero.recipes.data;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;

import java.util.ArrayList;
import java.util.Arrays;

public class MeasuresExamples {

    public static final LanguageEnumBO LANGUAGE_ENUM_ENGLISH = LanguageEnumBO.EN;
    public static final LanguageEnumBO LANGUAGE_ENUM_SPANISH = LanguageEnumBO.ES;

    public static final String MEASURE_01_ID = "tablespoon";
    public static final String MEASURE_01_NAME_ENGLISH_SINGULAR = "tablespoon";
    public static final String MEASURE_01_NAME_ENGLISH_PLURAL = "tablespoons";
    public static final DescriptiveNameBO MEASURE_01_NAME_ENGLISH = new DescriptiveNameBO(MEASURE_01_NAME_ENGLISH_SINGULAR, MEASURE_01_NAME_ENGLISH_PLURAL, LANGUAGE_ENUM_ENGLISH);
    public static final String MEASURE_01_NAME_SPANISH_SINGULAR = "cucharada";
    public static final String MEASURE_01_NAME_SPANISH_PLURAL = "cucharadas";
    public static final DescriptiveNameBO MEASURE_01_NAME_SPANISH = new DescriptiveNameBO(MEASURE_01_NAME_SPANISH_SINGULAR, MEASURE_01_NAME_SPANISH_PLURAL, LANGUAGE_ENUM_SPANISH);
    public static final MeasureBO MEASURE_BO_01_ENGLISH = new MeasureBO(MEASURE_01_ID, MEASURE_01_NAME_ENGLISH);
    public static final MeasureBO MEASURE_BO_01_SPANISH = new MeasureBO(MEASURE_01_ID, MEASURE_01_NAME_SPANISH);

    public static final String MEASURE_02_ID = "cup";
    public static final String MEASURE_02_NAME_ENGLISH_SINGULAR = "cup";
    public static final String MEASURE_02_NAME_ENGLISH_PLURAL = "cups";
    public static final DescriptiveNameBO MEASURE_02_NAME_ENGLISH = new DescriptiveNameBO(MEASURE_02_NAME_ENGLISH_SINGULAR, MEASURE_02_NAME_ENGLISH_PLURAL, LANGUAGE_ENUM_ENGLISH);
    public static final String MEASURE_02_NAME_SPANISH_SINGULAR = "taza";
    public static final String MEASURE_02_NAME_SPANISH_PLURAL = "tazas";
    public static final DescriptiveNameBO MEASURE_02_NAME_SPANISH = new DescriptiveNameBO(MEASURE_02_NAME_SPANISH_SINGULAR, MEASURE_02_NAME_SPANISH_PLURAL, LANGUAGE_ENUM_SPANISH);
    public static final MeasureBO MEASURE_BO_02_ENGLISH = new MeasureBO(MEASURE_02_ID, MEASURE_02_NAME_ENGLISH);
    public static final MeasureBO MEASURE_BO_02_SPANISH = new MeasureBO(MEASURE_02_ID, MEASURE_02_NAME_SPANISH);


    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_ES = "es";

    public static final MeasurePO MEASURE_PO_01 = new MeasurePO(MEASURE_01_ID, Arrays.asList(
            new DescriptiveNamePO(MEASURE_01_NAME_ENGLISH_SINGULAR, MEASURE_01_NAME_ENGLISH_PLURAL, LANGUAGE_EN),
            new DescriptiveNamePO(MEASURE_01_NAME_SPANISH_SINGULAR, MEASURE_01_NAME_SPANISH_PLURAL, LANGUAGE_ES)));

    public static final MeasurePO MEASURE_PO_02 = new MeasurePO(MEASURE_02_ID, Arrays.asList(
            new DescriptiveNamePO(MEASURE_02_NAME_ENGLISH_SINGULAR, MEASURE_02_NAME_ENGLISH_PLURAL, LANGUAGE_EN),
            new DescriptiveNamePO(MEASURE_02_NAME_SPANISH_SINGULAR, MEASURE_02_NAME_SPANISH_PLURAL, LANGUAGE_ES)));

 /*   public static final MeasurePO MEASURE_PO_01_ONLY_ENGLISH = initMeNewOnlyEnglish();

    private static MeasurePO initCuisineTypeNewOnlyEnglish() {
        CuisineTypePO cuisineTypePO = new CuisineTypePO();
        cuisineTypePO.setObjectId(MEASURE_01_ID);
        ArrayList<LanguageNamePO>  names = new ArrayList<>();
        names.add(new LanguageNamePO(LANGUAGE_EN, MEASURE_01_NAME_ENGLISH));
        cuisineTypePO.setNames(names);
        return cuisineTypePO;
    }

    public static final CuisineTypeNew MEASURE_NEW = initCuisineTypeNew();
    public static final CuisineTypeNew MEASURE_NEW_NO_DEFAULT_LANGUAGE = initCuisineTypeNewNoDefaultLanguage();

    public static final CuisineTypeUpdate MEASURE_UPDATE_ES = new CuisineTypeUpdate(MEASURE_01_NAME_SPANISH);
    public static final CuisineTypeUpdate MEASURE_UPDATE_EN = new CuisineTypeUpdate(MEASURE_01_NAME_ENGLISH);

    public static final CuisineTypeMultiLanguageBO MEASURE_MULTI_LANGUAGE_BO = initCuisineTypeMultiLanguageBO();

    private static CuisineTypeNew initCuisineTypeNew() {
        return new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(MEASURE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(MEASURE_01_NAME_SPANISH, LanguageEnum.ES)));
    }

    private static CuisineTypeNew initCuisineTypeNewNoDefaultLanguage() {
        return new  CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(MEASURE_01_NAME_SPANISH, LanguageEnum.ES)));
    }

    private static CuisineTypeMultiLanguageBO initCuisineTypeMultiLanguageBO() {
        return new CuisineTypeMultiLanguageBO.Builder(Arrays.asList(
                LANGUAGE_NAME_BO_01_ENGLISH,LANGUAGE_NAME_BO_01_SPANISH), LANGUAGE_ENUM_ENGLISH).build();
    }
*/



}
