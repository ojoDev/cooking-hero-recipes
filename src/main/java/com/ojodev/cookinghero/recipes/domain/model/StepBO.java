package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Step to cook a recipe.
 */
@Data
@NoArgsConstructor
public class StepBO {

    private Integer id;

    private String description;

    private MediaRefBO media;

    public StepBO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public StepBO(Integer id, String description, MediaRefBO media) {
        this.id = id;
        this.description = description;
        this.media = media;
    }
}
