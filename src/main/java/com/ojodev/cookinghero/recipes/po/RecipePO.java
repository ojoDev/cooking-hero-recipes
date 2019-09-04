package com.ojodev.cookinghero.recipes.po;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="recipes")
public class RecipePO {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	@Field("cuisine-type")
	private List<String> cuisineType;
	
	private Decimal128 preparationTime;
	
	private Decimal128 difficulty;

	private String photoId;
	
	private List<StepPO> steps;
	
	private List<IngredientPO> ingredients;
	
	private String user;
	
	//TODO DMS: ¿DateTime?
	private DateTime creationDate;

	public RecipePO(ObjectId id, String name, String description, List<String> cuisineType, Decimal128 preparationTime,
			Decimal128 difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cuisineType = cuisineType;
		this.preparationTime = preparationTime;
		this.difficulty = difficulty;
	}
}
