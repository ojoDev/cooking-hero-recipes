package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
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

    private List<Ingredient> ingredients;


    public RecipePO() {
    }

    public RecipePO(String objectId, String name, String description) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
