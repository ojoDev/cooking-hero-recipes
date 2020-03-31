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

	private String description;

	@Relationship(type = "REPRESENTED_BY")
	private MediaPO media;

	@Relationship(type="INCLUDE", direction = Relationship.INCOMING)
	private RecipePO recipe;

	public StepPO() {
	}

	public StepPO(String objectId, String description) {
		this.objectId = objectId;
		this.description = description;
	}

	public StepPO(String objectId, String description, MediaPO media) {
		this.objectId = objectId;
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

	public MediaPO getMedia() {
		return media;
	}

	public void setMedia(MediaPO media) {
		this.media = media;
	}

	public RecipePO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipePO recipe) {
		this.recipe = recipe;
	}
}
