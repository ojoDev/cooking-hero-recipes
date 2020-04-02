package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.MediaTypeEnum;
import com.ojodev.cookinghero.recipes.domain.enume.MediaTypeEnumBO;
import com.ojodev.cookinghero.recipes.domain.enume.RecipeStatusEnumBO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

/**
 * Recipe created by a user.
 */
@Data
@NoArgsConstructor
public class RecipeBO {

    private String id;

    private String name;

    private String description;

    private List<CuisineTypeBO> cuisineTypes;

    private Integer preparationTime;

    private Integer difficulty;

    private MediaRefBO mainImage;

    private List<StepBO> steps;

    private List<IngredientBO> ingredients;

    private String userId;

    private LanguageEnumBO language;

    private DateTime creationDate;

    private DateTime updateDate;

    private RecipeStatusEnumBO status;

    public RecipeBO(String name, String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.userId = userId;
    }

    public RecipeBO(String id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public void setMainImage(MediaRefBO mainImage) {
        if (mainImage != null && !MediaTypeEnumBO.IMAGE.equals(mainImage.getMediaType())) {
            throw new IllegalArgumentException("mainImage must be IMAGE type");
        }
        this.mainImage = mainImage;
    }
}
