package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "REPRESENTED_BY")
public class RecipeCuisineTypeRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private RecipePO recipe;

    @EndNode
    private CuisineTypePO cuisineType;


    public RecipeCuisineTypeRelationship(RecipePO recipe, CuisineTypePO cuisineType) {
        this.recipe = recipe;
        this.cuisineType = cuisineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipePO getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipePO recipe) {
        this.recipe = recipe;
    }

    public CuisineTypePO getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineTypePO cuisineType) {
        this.cuisineType = cuisineType;
    }
}
