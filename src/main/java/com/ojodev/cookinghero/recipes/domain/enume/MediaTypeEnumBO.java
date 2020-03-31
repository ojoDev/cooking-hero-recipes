package com.ojodev.cookinghero.recipes.domain.enume;

public enum MediaTypeEnumBO {

		IMAGE("IMAGE"),
		VIDEO("VIDEO");

		private String value;

		MediaTypeEnumBO(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
}
