package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Recipe created by a user.
 */
@Data
public class RecipeNewBO {

    @Getter
    private String id;

    private String name;

    private String description;

    private Integer preparationTime;

    private Integer difficulty;

    private String userId;

    private LanguageEnumBO language;

    public RecipeNewBO(@NotNull String name, @NotNull String userI, @NotNull LanguageEnumBO language) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId;
        this.language = language;
    }

}
