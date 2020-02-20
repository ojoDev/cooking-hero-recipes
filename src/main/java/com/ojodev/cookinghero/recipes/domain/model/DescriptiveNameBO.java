package com.ojodev.cookinghero.recipes.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Define a name to describe a item associated with a quantity, in singular or plural form.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptiveNameBO {

    private String singular;

    private String plural;

    private LanguageEnumBO language;

}
