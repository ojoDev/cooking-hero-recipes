package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LanguageEnumTest {

    private static final String ENGLISH = "en";
    private static final String SPANISH = "es";
    private static final String INVALID = "xx";

    @Test
    public void initFromValue() {
        LanguageEnum languageEnumEn = LanguageEnum.fromValue(ENGLISH);
        LanguageEnum languageEnumEs = LanguageEnum.fromValue(SPANISH);

        assertEquals(LanguageEnum.EN, languageEnumEn);
        assertEquals(LanguageEnum.ES, languageEnumEs);
    }

    @Test
    public void initFromInvalidValue() {
        LanguageEnum languageEnum = LanguageEnum.fromValue(INVALID);

        assertNull(languageEnum);
    }

    @Test
    public void getValueList() {
        assertEquals("en, es", LanguageEnum.getValueList());
    }
}
