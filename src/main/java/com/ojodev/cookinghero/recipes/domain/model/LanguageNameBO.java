package com.ojodev.cookinghero.recipes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Name associated with a language.
 */
@Data
@AllArgsConstructor
public class LanguageNameBO {

    private String name;

    private LanguageEnumBO language;
}
