package com.ojodev.cookinghero.recipes.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Cuisine type with multiple languages associated.
 *
 */
@ToString
@EqualsAndHashCode
@Getter
public class CuisineTypeMultiLanguageBO {

    private String id; // Generated by languangeName.language = default
    private List<LanguageNameBO> languageNames;  //Mandatory

    private CuisineTypeMultiLanguageBO(Builder builder) {
        this.id = builder.getId();
        this.languageNames = builder.languageNames;
    }


    public static class Builder extends IdentifiableBO{

       private List<LanguageNameBO> languageNames = new ArrayList<>();

        private LanguageEnumBO defaultLanguage;

        public Builder(List<LanguageNameBO> languageNames, LanguageEnumBO defaultLanguage ) {
            super("");
            this.defaultLanguage = defaultLanguage;
            this.languageNames(languageNames);
        }

        public Builder id(String id) {
            setId(id);
            return this;
        }

        public Builder languageName(LanguageNameBO newLanguageName) {
            validateLanguageName(newLanguageName);
            if (languageExists(newLanguageName)) {
                replaceLanguage(newLanguageName);
            } else {
                addLanguage(newLanguageName);
            }
            if (languageIsDefault(newLanguageName)) {
                setId(newLanguageName.getName());
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

        private boolean languageIsDefault(LanguageNameBO newLanguageName) {
            return defaultLanguage != null && defaultLanguage == (newLanguageName.getLanguage());
        }


        public Builder languageNames(List<LanguageNameBO> languageNames) {
            languageNames.stream().forEach(ln -> languageName(ln));
            // this.languageNames = languageNames;
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
            if (cuisineTypeMultiLanguageBO.getLanguageNames().stream().filter(ln -> defaultLanguage.equals(ln.getLanguage())).count() == 0) {
                throw new IllegalArgumentException("CuisineType needs to include default language name: " + defaultLanguage);
            }
        }


    }

}
