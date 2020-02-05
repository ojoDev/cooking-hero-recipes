package com.ojodev.cookinghero.recipes.config;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
public class RecipesConfig {

    private LanguageEnumBO defaultLanguage;

    public RecipesConfig(@Value("${language.default}") LanguageEnumBO defaultLanguage) {
        this.defaultLanguage = defaultLanguage;

    }


}
