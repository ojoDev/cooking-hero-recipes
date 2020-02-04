package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ToString
@Getter
public class CuisineTypeMultiLanguageBO {

    private static final LanguageEnumBO DEFAULT_LANGUAGE = LanguageEnumBO.EN;

    private String id; // Generated by languangeName.language = default
    private List<LanguageNameBO> languageNames;  //Mandatory

    private CuisineTypeMultiLanguageBO(Builder builder) {
        this.id = builder.id;
        this.languageNames = languageNames;
    }


    public static class Builder {
       private String id;
       private List<LanguageNameBO> languageNames;

        private LanguageEnumBO defaultLanguage;

        public Builder(List<LanguageNameBO> languageNames, LanguageEnumBO defaultLanguage ) {
            this.languageNames = languageNames;
            this.defaultLanguage = defaultLanguage;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder languageName(LanguageNameBO newLanguageName) {
            validateLanguageName(newLanguageName);
            if (languageExists(newLanguageName)) {
                replaceLanguage(newLanguageName);
            } else {
                addLanguage(newLanguageName);
            }
            return this;
        }

        private boolean languageExists(LanguageNameBO newLanguageName) {
            return this.languageNames.stream().filter(ln -> ln.getLanguage() == newLanguageName.getLanguage()).findAny().isPresent();
        }

        private Builder replaceLanguage(LanguageNameBO newLanguageName) {
            this.languageNames.stream().filter(ln -> ln.getLanguage() == newLanguageName.getLanguage()).forEach(ln -> ln.setName(newLanguageName.getName()));
            return this;
        }

        private Builder addLanguage(LanguageNameBO newLanguageName) {
            this.languageNames.add(newLanguageName);
            return this;
        }


        public Builder languageNames(List<LanguageNameBO> languageNames) {
            languageNames.stream().forEach(ln -> validateLanguageName(ln));
            this.languageNames = languageNames;
            return this;
        }

        public CuisineTypeMultiLanguageBO build() {
            CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = new CuisineTypeMultiLanguageBO(this);
            validateCuisineTypeObject(cuisineTypeMultiLanguageBO);
            return cuisineTypeMultiLanguageBO;
        }

        private void validateLanguageName(LanguageNameBO languageName) {
            if (languageName == null || StringUtils.isEmpty(languageName.getName())) {
                throw new IllegalArgumentException("Language name needs to include at least name and language fields");
            }
        }


        private void validateCuisineTypeObject(CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO) {
            if (cuisineTypeMultiLanguageBO.getLanguageNames() == null || cuisineTypeMultiLanguageBO.getLanguageNames().size() == 0) {
                throw new IllegalArgumentException("CuisineType needs to include at least one name");
            }
            if (cuisineTypeMultiLanguageBO.getLanguageNames().stream().filter(ln -> DEFAULT_LANGUAGE.equals(ln.getLanguage())).count() == 0) {
                throw new IllegalArgumentException("CuisineType needs to include default language name: " + DEFAULT_LANGUAGE);
            }
        }


    }

}
