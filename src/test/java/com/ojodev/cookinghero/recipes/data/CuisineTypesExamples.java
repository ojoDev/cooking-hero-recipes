package com.ojodev.cookinghero.recipes.data;

import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeUpdate;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;

import java.util.ArrayList;
import java.util.Arrays;

public class CuisineTypesExamples {

    public static final LanguageEnumBO LANGUAGE_ENUM_ENGLISH = LanguageEnumBO.EN;
    public static final LanguageEnumBO LANGUAGE_ENUM_SPANISH = LanguageEnumBO.ES;

    public static final String CUISINE_TYPE_01_ID = "italian";
    public static final String CUISINE_TYPE_01_NAME_SPANISH = "italiana";
    public static final String CUISINE_TYPE_01_NAME_ENGLISH = "italian";
    public static final CuisineTypeBO CUISINE_TYPE_BO_01_ENGLISH = new CuisineTypeBO(CUISINE_TYPE_01_ID, CUISINE_TYPE_01_NAME_ENGLISH, LANGUAGE_ENUM_ENGLISH);
    public static final CuisineTypeBO CUISINE_TYPE_BO_01_SPANISH = new CuisineTypeBO(CUISINE_TYPE_01_ID, CUISINE_TYPE_01_NAME_SPANISH, LANGUAGE_ENUM_SPANISH);

    public static final String CUISINE_TYPE_02_ID = "japanese";
    public static final String CUISINE_TYPE_02_NAME_ENGLISH = "japanese";
    public static final String CUISINE_TYPE_02_NAME_SPANISH = "japonesa";
    public static final CuisineTypeBO CUISINE_TYPE_BO_02_ENGLISH = new CuisineTypeBO(CUISINE_TYPE_02_ID, CUISINE_TYPE_02_NAME_ENGLISH, LANGUAGE_ENUM_ENGLISH);
    public static final CuisineTypeBO CUISINE_TYPE_BO_02_SPANISH = new CuisineTypeBO(CUISINE_TYPE_02_ID, CUISINE_TYPE_02_NAME_SPANISH, LANGUAGE_ENUM_SPANISH);

    public static final String CUISINE_TYPE_03_ID = "veggie";
    public static final String CUISINE_TYPE_03_NAME_ENGLISH = "veggie";
    public static final String CUISINE_TYPE_03_NAME_SPANISH = "vegatariana";
    public static final CuisineTypeBO CUISINE_TYPE_BO_03_ENGLISH = new CuisineTypeBO(CUISINE_TYPE_03_ID, CUISINE_TYPE_03_NAME_ENGLISH, LANGUAGE_ENUM_ENGLISH);
    public static final CuisineTypeBO CUISINE_TYPE_BO_03_SPANISH = new CuisineTypeBO(CUISINE_TYPE_03_ID, CUISINE_TYPE_03_NAME_SPANISH, LANGUAGE_ENUM_SPANISH);

    public static final LanguageNameBO LANGUAGE_NAME_BO_01_ENGLISH = new LanguageNameBO(CUISINE_TYPE_01_NAME_ENGLISH, LANGUAGE_ENUM_ENGLISH);
    public static final LanguageNameBO LANGUAGE_NAME_BO_02_ENGLISH = new LanguageNameBO(CUISINE_TYPE_02_NAME_ENGLISH, LANGUAGE_ENUM_ENGLISH);
    public static final LanguageNameBO LANGUAGE_NAME_BO_01_SPANISH = new LanguageNameBO(CUISINE_TYPE_01_NAME_SPANISH, LANGUAGE_ENUM_SPANISH);
    public static final LanguageNameBO LANGUAGE_NAME_BO_02_SPANISH = new LanguageNameBO(CUISINE_TYPE_02_NAME_SPANISH, LANGUAGE_ENUM_SPANISH);

    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_ES = "es";

    public static final CuisineTypePO CUISINE_TYPE_PO_01 = new CuisineTypePO(CUISINE_TYPE_01_ID, Arrays.asList(
            new LanguageNamePO(LANGUAGE_EN, CUISINE_TYPE_01_NAME_ENGLISH),
            new LanguageNamePO(LANGUAGE_ES, CUISINE_TYPE_01_NAME_SPANISH)));
    public static final CuisineTypePO CUISINE_TYPE_PO_02 = new CuisineTypePO(CUISINE_TYPE_02_ID, Arrays.asList(
            new LanguageNamePO(LANGUAGE_EN, CUISINE_TYPE_02_NAME_ENGLISH),
            new LanguageNamePO(LANGUAGE_ES, CUISINE_TYPE_02_NAME_SPANISH)));
    public static final CuisineTypePO CUISINE_TYPE_PO_03 = new CuisineTypePO(CUISINE_TYPE_03_ID, Arrays.asList(
            new LanguageNamePO(LANGUAGE_EN, CUISINE_TYPE_03_NAME_ENGLISH),
            new LanguageNamePO(LANGUAGE_ES, CUISINE_TYPE_03_NAME_SPANISH)));
    public static final CuisineTypePO CUISINE_TYPE_PO_01_ONLY_ENGLISH = initCuisineTypeNewOnlyEnglish();

    private static CuisineTypePO initCuisineTypeNewOnlyEnglish() {
        CuisineTypePO cuisineTypePO = new CuisineTypePO();
        cuisineTypePO.setObjectId(CUISINE_TYPE_01_ID);
        ArrayList<LanguageNamePO>  names = new ArrayList<>();
        names.add(new LanguageNamePO(LANGUAGE_EN, CUISINE_TYPE_01_NAME_ENGLISH));
        cuisineTypePO.setNames(names);
        return cuisineTypePO;
    }

    public static final CuisineTypeNew CUISINE_TYPE_NEW = initCuisineTypeNew();
    public static final CuisineTypeNew CUISINE_TYPE_NEW_NO_DEFAULT_LANGUAGE = initCuisineTypeNewNoDefaultLanguage();

    public static final CuisineTypeUpdate CUISINE_TYPE_UPDATE_ES = new CuisineTypeUpdate(CUISINE_TYPE_01_NAME_SPANISH);
    public static final CuisineTypeUpdate CUISINE_TYPE_UPDATE_EN = new CuisineTypeUpdate(CUISINE_TYPE_01_NAME_ENGLISH);

    public static final CuisineTypeMultiLanguageBO CUISINE_TYPE_MULTI_LANGUAGE_BO = initCuisineTypeMultiLanguageBO();

    private static CuisineTypeNew initCuisineTypeNew() {
        return new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));
    }

    private static CuisineTypeNew initCuisineTypeNewNoDefaultLanguage() {
        return new  CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));
    }

    private static CuisineTypeMultiLanguageBO initCuisineTypeMultiLanguageBO() {
        return new CuisineTypeMultiLanguageBO.Builder(Arrays.asList(
                LANGUAGE_NAME_BO_01_ENGLISH,LANGUAGE_NAME_BO_01_SPANISH), LANGUAGE_ENUM_ENGLISH).build();
    }




}
