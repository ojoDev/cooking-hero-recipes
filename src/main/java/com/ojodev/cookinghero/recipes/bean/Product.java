package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;










/**
 * Product
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-23T17:27:32.003+01:00[Europe/Paris]")

public class Product   {

  
    

    
  
  @JsonProperty("name")
  
  
  
  
  
  
  private String name = null;
  

  
  
  public Product name(String name) {
    this.name = name;
    return this;
  }
  
  

  /**
  
   * product name
  
  
  
  
   * @return name
  **/
 
  @ApiModelProperty(example = "potatoe", required = true, value = "product name")

  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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




