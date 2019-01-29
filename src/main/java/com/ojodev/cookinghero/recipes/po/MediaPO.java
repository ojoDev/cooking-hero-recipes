package com.ojodev.cookinghero.recipes.po;

import com.ojodev.cookinghero.recipes.enume.MediaTypeEnum;

public class MediaPO {
	
	private MediaTypeEnum type;
	
	private String id;

	public MediaTypeEnum getType() {
		return type;
	}

	public void setType(MediaTypeEnum type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
