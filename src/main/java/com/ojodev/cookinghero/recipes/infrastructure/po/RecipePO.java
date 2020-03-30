package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.joda.time.DateTime;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Recipe created by a user.
 */
@NodeEntity(label = "Recipe")
public class RecipePO {

    @Id
    @GeneratedValue
    private Long id;

    private String objectId;

    private String name;

    private String description;

    @Relationship(type = "REPRESENTED_BY")
    private List<CuisineTypePO> cuisineType;

    private Integer preparationTime;

    private Integer difficulty;

    @Relationship(type = "REPRESENTED_BY")
    private MediaPO mainImage;

    @Relationship(type = "INCLUDE")
    private List<StepPO> steps;

    @Relationship(type = "INCLUDE")
    private List<IngredientPO> ingredients;

    private String userId;

    private String language;

    private DateTime creationDate;

    private DateTime updateDate;

    private String status;


    public RecipePO() {
    }

    public RecipePO(String objectId, String name) {
        this.objectId = objectId;
        this.name = name;
    }

    public RecipePO(String objectId, String name, String description, LanguageEnumBO language) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        this.language = language.toString();
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

    public List<CuisineTypePO> getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(List<CuisineTypePO> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public MediaPO getMainImage() {
        return mainImage;
    }

    public void setMainImage(MediaPO mainImage) {
        this.mainImage = mainImage;
    }

    public List<StepPO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepPO> steps) {
        this.steps = steps;
    }

    public List<IngredientPO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientPO> ingredients) {
        this.ingredients = ingredients;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
