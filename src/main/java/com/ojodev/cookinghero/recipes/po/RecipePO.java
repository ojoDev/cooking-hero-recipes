package com.ojodev.cookinghero.recipes.po;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="recipes")
public class RecipePO {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	@Field("cousine_type")
	private List<String> cousineType;

	private Decimal128 length;

	private Decimal128 difficulty;


	public RecipePO() {
		super();
	}

	public RecipePO(ObjectId id, String name, String description, List<String> cousineType, Decimal128 length,
			Decimal128 difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cousineType = cousineType;
		this.length = length;
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

	public Decimal128 getLength() {
		return length;
	}

	public void setLength(Decimal128 length) {
		this.length = length;
	}

	public Decimal128 getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Decimal128 difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "RecipePO [id=" + id + ", name=" + name + ", description=" + description + ", cousineType=" + cousineType
				+ ", length=" + length + ", difficulty=" + difficulty + "]";
	}

		
}
