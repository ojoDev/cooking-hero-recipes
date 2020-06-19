package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Step to cook a recipe.
 */
@Data
@NoArgsConstructor
public class StepBO {

    private String id;

    private Integer position;

    private String description;

    private MediaRefBO media;

    public StepBO(@NotNull String id, @NotNull Integer position, @NotNull String description) {
        this.id = id;
        this.position = position;
        this.description = description;
    }

    public StepBO(@NotNull String id, @NotNull Integer position, @NotNull String description, MediaRefBO media) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.media = media;
    }
}
