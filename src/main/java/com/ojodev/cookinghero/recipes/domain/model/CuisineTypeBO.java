package com.ojodev.cookinghero.recipes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cuisine type.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTypeBO {

    private String id;

    private String name;

    private LanguageEnumBO language;

}
