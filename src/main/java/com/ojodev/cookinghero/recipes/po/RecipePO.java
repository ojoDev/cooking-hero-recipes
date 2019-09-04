package com.ojodev.cookinghero.recipes.po;

import java.util.List;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection="recipes")
public class RecipePO {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	@Field("cousine_type")
	private List<String> cousineType;
	
	private Decimal128 preparationTime;
	
	private Decimal128 difficulty;

	private String photoId;
	
	private List<StepPO> steps;
	
	private List<IngredientPO> ingredients;
	
	private String user;
	
	//TODO DMS: ¿DateTime?
	private DateTime creationDate;
	
	
	public RecipePO() {
		super();
	}

	public RecipePO(ObjectId id, String name, String description, List<String> cousineType, Decimal128 preparationTime,
			Decimal128 difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cousineType = cousineType;
		this.preparationTime = preparationTime;
		this.difficulty = difficulty;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public List<String> getCousineType() {
		return cousineType;
	}

	public void setCousineType(List<String> cousineType) {
		this.cousineType = cousineType;
	}

	public Decimal128 getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Decimal128 preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Decimal128 getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Decimal128 difficulty) {
		this.difficulty = difficulty;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
	
}
