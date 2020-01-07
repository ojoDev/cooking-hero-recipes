package com.ojodev.cookinghero.recipes.domain.enume;

public enum MediaTypeEnum {

		PHOTO("PHOTO"),
		VIDEO("VIDEO");

		private String value;

		MediaTypeEnum(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
}
