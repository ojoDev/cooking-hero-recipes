package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Recipe created by a user.
 */
@Data
@NoArgsConstructor
public class RecipeNewBO {

    @Getter
    private String id;

    private String name;

    private String description;

    private Integer preparationTime;

    private Integer difficulty;

    private String userId;

    public RecipeNewBO(@NotNull String name, @NotNull String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId;
    }

}
