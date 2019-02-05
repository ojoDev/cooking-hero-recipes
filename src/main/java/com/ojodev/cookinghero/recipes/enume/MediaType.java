package com.ojodev.cookinghero.recipes.enume;

public enum MediaType {

		PHOTO("PHOTO"),
		VIDEO("VIDEO");

		private String value;

		MediaType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
}
