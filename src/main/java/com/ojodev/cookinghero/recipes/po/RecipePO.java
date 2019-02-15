package com.ojodev.cookinghero.recipes.po;

import java.util.List;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection="recipes")
public class RecipePO {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	@Field("cousine_type")
	private List<String> cousineType;

	private Decimal128 cookingTime;
	
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

	public RecipePO(ObjectId id, String name, String description, List<String> cousineType, Decimal128 cookingTime,
			Decimal128 difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cousineType = cousineType;
		this.cookingTime = cookingTime;
		this.difficulty = difficulty;
	}
	
}
