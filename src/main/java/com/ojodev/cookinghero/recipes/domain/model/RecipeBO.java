package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.*;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

/**
 * Recipe created by a user.
 */
@Data
public class RecipeBO {

    private String id;

    private String name;

    private String description;

    private List<CuisineType> cuisineType;

    private Integer preparationTime;

    private Integer difficulty;

    private Media mainImage;

    private List<Step> steps;

    private List<Ingredient> ingredients;

    private String userId;

    private LanguageEnum language;

    private DateTime creationDate;

    private DateTime updateDate;

    private RecipeStatusEnum status;

    public RecipeBO(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public RecipeBO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
