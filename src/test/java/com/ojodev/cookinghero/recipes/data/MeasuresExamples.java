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

    public static final MeasurePO MEASURE_PO_01_ONLY_ENGLISH = initMeasureOnlyEnglish();

    public static final MeasureNew MEASURE_NEW = initMeasureNew();
    public static final MeasureNew MEASURE_NEW_NO_DEFAULT_LANGUAGE = initMeasureNewNoDefaultLanguage();

    public static final MeasureMultiLanguageBO MEASURE_MULTI_LANGUAGE_BO = initMeasureMultiLanguageBO();

    public static final String MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED = "tablespoon_xxx";
    public static final String MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED = "tablespoons_xxx";
    public static final String MEASURE_01_NAME_SPANISH_SINGULAR_CHANGED = "cucharada_xxx";
    public static final String MEASURE_01_NAME_SPANISH_PLURAL_CHANGED = "cucharadas_xxx";

    public static final MeasureUpdate MEASURE_UPDATE_COMPLETE = new MeasureUpdate(new DescriptiveNameUpdate(MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED));
    public static final MeasureUpdate MEASURE_UPDATE_PARTIAL = initPartialMeasureUpdate();
    public static final MeasureUpdate MEASURE_UPDATE_PARTIAL_AND_NULL = initPartialAndNullMeasureUpdate();


    private static MeasurePO initMeasureOnlyEnglish() {
        MeasurePO measurePO = new MeasurePO();
        measurePO.setObjectId(MEASURE_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(MEASURE_01_NAME_ENGLISH_SINGULAR, MEASURE_01_NAME_ENGLISH_PLURAL, LANGUAGE_EN));
        measurePO.setNames(names);
        return measurePO;
    }

    private static MeasureNew initMeasureNew() {
        return new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MEASURE_01_NAME_ENGLISH_SINGULAR, MEASURE_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new MeasureNewName(new DescriptiveName(MEASURE_01_NAME_SPANISH_SINGULAR, MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));
    }

    private static MeasureNew initMeasureNewNoDefaultLanguage() {
        return new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MEASURE_01_NAME_SPANISH_SINGULAR, MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));
    }

    private static MeasureMultiLanguageBO initMeasureMultiLanguageBO() {
        return new MeasureMultiLanguageBO.Builder(Arrays.asList(
                MEASURE_01_NAME_ENGLISH, MEASURE_01_NAME_SPANISH), LANGUAGE_ENUM_ENGLISH).build();
    }

    private static MeasureUpdate initPartialMeasureUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED);
        return new MeasureUpdate(descriptiveNameUpdate);
    }

    private static MeasureUpdate initPartialAndNullMeasureUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED);
        descriptiveNameUpdate.setPlural(null);
        return new MeasureUpdate(descriptiveNameUpdate);
    }

}
