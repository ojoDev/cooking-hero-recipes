package com.ojodev.cookinghero.recipes.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageEnumBOTest {

    private static final String ENGLISH = "en";
    private static final String SPANISH = "es";
    private static final String INVALID = "xx";

    @Test
    public void initFromValue(){
        LanguageEnumBO languageEnumBOEn = LanguageEnumBO.fromValue(ENGLISH);
        LanguageEnumBO languageEnumBOEs = LanguageEnumBO.fromValue(SPANISH);

        assertEquals(LanguageEnumBO.EN, languageEnumBOEn);
        assertEquals(LanguageEnumBO.ES, languageEnumBOEs);
    }

    @Test
    public void initFromInvalidValue(){
        LanguageEnumBO languageEnumBO = LanguageEnumBO.fromValue(INVALID);

        assertNull(languageEnumBO);
    }

    @Test
    public void getValueList(){
        LanguageEnumBO languageEnumBO = LanguageEnumBO.fromValue(INVALID);

        assertEquals("en, es", LanguageEnumBO.getValueList());
    }
}
