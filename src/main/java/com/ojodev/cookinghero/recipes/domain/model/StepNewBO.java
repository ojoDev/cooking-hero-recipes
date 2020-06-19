package com.ojodev.cookinghero.recipes.domain.model;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Step to cook a recipe.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StepNewBO extends IdentifiableBO {

    private String recipeId;

    private Integer position;

    private String description;

    private MediaRefBO media;

    public StepNewBO(@NotNull String recipeId, @NotNull Integer position, @NotNull String description) {
        super(recipeId + "-" + position);
        this.recipeId = recipeId;
        this.position = position;
        this.description = description;
    }

    public StepNewBO(@NotNull String recipeId, @NotNull Integer position, @NotNull String description, MediaRefBO media) {
        super(recipeId + "-" + position);
        this.recipeId = recipeId;
        this.position = position;
        this.description = description;
        this.media = media;
    }
}
