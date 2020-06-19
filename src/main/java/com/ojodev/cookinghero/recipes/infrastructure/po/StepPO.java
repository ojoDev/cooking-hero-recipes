package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label="Step")
public class StepPO {

	@Id
	@GeneratedValue
	private Long id;

	private String objectId;

	private Integer position;

	private String description;

	@Relationship(type = "REPRESENTED_BY")
	private MediaRefPO media;

	@Relationship(type="INCLUDE", direction = Relationship.INCOMING)
	private RecipePO recipe;

	public StepPO() {
	}

	public StepPO(String objectId, Integer position, String description) {
		this.objectId = objectId;
		this.position = position;
		this.description = description;
	}

	public StepPO(String objectId,  Integer position, String description, MediaRefPO media) {
		this.objectId = objectId;
		this.position = position;
		this.description = description;
		this.media = media;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public MediaRefPO getMedia() {
		return media;
	}

	public void setMedia(MediaRefPO media) {
		this.media = media;
	}

	public RecipePO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipePO recipe) {
		this.recipe = recipe;
	}
}
