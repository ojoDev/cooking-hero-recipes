package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Ingredient")
public class IngredientPO {

    @Id
    @GeneratedValue
    private Long id;

    private String objectId;

    @Relationship(type = "FORMED_BY")
    private ProductPO product;

    private Integer quantity;

    @Relationship(type = "FORMED_BY")
    private MeasurePO measure;

    @Relationship(type="INCLUDE", direction = Relationship.INCOMING)
    private RecipePO recipe;


    public IngredientPO() {
        super();
    }

    public IngredientPO(String objectId, ProductPO product) {
    	this.objectId = objectId;
        this.product = product;
    }

    public IngredientPO(String objectId, ProductPO product, Integer quantity, MeasurePO measure) {
		this.objectId = objectId;
        this.product = product;
        this.quantity = quantity;
        this.measure = measure;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public ProductPO getProduct() {
        return product;
    }

    public void setProduct(ProductPO product) {
        this.product = product;
    }

    public MeasurePO getMeasure() {
        return measure;
    }

    public void setMeasure(MeasurePO measure) {
        this.measure = measure;
    }

    public RecipePO getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipePO recipe) {
        this.recipe = recipe;
    }
}
