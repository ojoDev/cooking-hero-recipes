package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;










/**
 * RecipePhoto
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-23T17:27:32.003+01:00[Europe/Paris]")

public class RecipePhoto   {

  
    

    
  
  @JsonProperty("id")
  
  
  
  
  
  
  private String id = null;
  

  
    

    
  
  @JsonProperty("main")
  
  
  
  
  
  
  private Boolean main = null;
  

  
  
  public RecipePhoto id(String id) {
    this.id = id;
    return this;
  }
  
  

  /**
  
   * photo id
  
  
  
  
   * @return id
  **/
 
  @ApiModelProperty(example = "88484351313003", required = true, value = "photo id")

  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
  public RecipePhoto main(Boolean main) {
    this.main = main;
    return this;
  }
  
  

  /**
  
   * is the main photo of the recipe?
  
  
  
  
   * @return main
  **/
 
  @ApiModelProperty(required = true, value = "is the main photo of the recipe?")

  @NotNull


  public Boolean isMain() {
    return main;
  }

  public void setMain(Boolean main) {
    this.main = main;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipePhoto recipePhoto = (RecipePhoto) o;
    return Objects.equals(this.id, recipePhoto.id) &&
        Objects.equals(this.main, recipePhoto.main);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, main);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecipePhoto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    main: ").append(toIndentedString(main)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}




