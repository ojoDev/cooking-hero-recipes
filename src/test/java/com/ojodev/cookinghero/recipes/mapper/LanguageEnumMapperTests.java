package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageEnumMapperTests {


    @Autowired
    private LanguageEnumMapper languageEnumMapper;

    @Test
    public void convertLanguageEnumToLanguageEnumBO() {
        LanguageEnumBO languageEnumBOEn = languageEnumMapper.toLanguageEnumBO(LanguageEnum.EN);
        assertNotNull(languageEnumBOEn);
        assertEquals(LanguageEnumBO.EN, languageEnumBOEn);

        LanguageEnumBO languageEnumBOEs = languageEnumMapper.toLanguageEnumBO(LanguageEnum.ES);
        assertNotNull(languageEnumBOEs);
        assertEquals(LanguageEnumBO.ES, languageEnumBOEs);
    }

    @Test
    public void convertLanguageEnumBOToLanguageEnum() {
        LanguageEnum languageEnumEn = languageEnumMapper.toLanguageEnum(LanguageEnumBO.EN);
        assertNotNull(languageEnumEn);
        assertEquals(LanguageEnum.EN, languageEnumEn);

        LanguageEnum languageEnumEs = languageEnumMapper.toLanguageEnum(LanguageEnumBO.ES);
        assertNotNull(languageEnumEs);
        assertEquals(LanguageEnum.ES, languageEnumEs);

    }
}
