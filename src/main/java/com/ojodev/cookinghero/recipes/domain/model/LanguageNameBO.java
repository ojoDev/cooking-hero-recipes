package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
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

    private LanguageEnum language;
}
