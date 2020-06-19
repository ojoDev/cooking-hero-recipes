package com.ojodev.cookinghero.recipes.api.model;

import lombok.Getter;

@Getter
public enum ApiTags {

  CUISINE_TYPES("cuisine-types", "Cuisine types of recipes"),
  MEASURES("measures", "Measures used with ingredients"),
  PRODUCTS("products", "Products used in recipes"),
  RECIPES("recipes", "Hero recipes");


  private final String value;
  private final String desciption;


  ApiTags(String value, String description) {
    this.value = value;
    this.desciption = description;
  }


}
