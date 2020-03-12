package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;


@NodeEntity(label = "Recipe")
public class RecipePO {

    @Id
    @GeneratedValue
    private Long id;

    private String objectId;

    private String name;

    private String description;

    private List<IngredientPO> ingredients;

    private List<StepPO> steps;

    private String language;


    public RecipePO() {
    }

    public RecipePO(String objectId, String name, String description, LanguageEnumBO language) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        if (language != null) {
            this.language = language.toString();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientPO> getIngredients() {
        return ingredients;
    }

    public List<StepPO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepPO> steps) {
        this.steps = steps;
    }

    public void setIngredients(List<IngredientPO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
