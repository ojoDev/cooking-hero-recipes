package com.ojodev.cookinghero.recipes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Name associated with a language.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageNameBO {

    private String name;

    private LanguageEnumBO language;
}
