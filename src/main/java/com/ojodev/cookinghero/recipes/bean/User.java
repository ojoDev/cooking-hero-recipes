package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;










/**
 * User
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-23T17:27:32.003+01:00[Europe/Paris]")

public class User   {

  
    

    
  
  @JsonProperty("username")
  private String username;
  

  
    

    
  
  @JsonProperty("name")
  
  
  
  
  
  
  private String name = null;
  

  
    

    
  
  @JsonProperty("email")
  
  
  
  
  
  
  private String email = null;
  

  
    

    
  
  @JsonProperty("active")
  
  
  
  
  
  
  private Boolean active = null;
  

  
  
  public User username(String username) {
    this.username = username;
    return this;
  }
  
  

  /**
  
   * user id name
  
  
  
  
   * @return username
  **/
 
  @ApiModelProperty(example = "ojodev", value = "user id name")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  
  public User name(String name) {
    this.name = name;
    return this;
  }
  
  

  /**
  
   * user real name
  
  
  
  
   * @return name
  **/
 
  @ApiModelProperty(example = "david", value = "user real name")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  
  public User email(String email) {
    this.email = email;
    return this;
  }
  
  

  /**
  
   * user personal email
  
  
  
  
   * @return email
  **/
 
  @ApiModelProperty(example = "ojodev@gmail.com", value = "user personal email")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
  public User active(Boolean active) {
    this.active = active;
    return this;
  }
  
  

  /**
  
   * user is active or not
  
  
  
  
   * @return active
  **/
 
  @ApiModelProperty(value = "user is active or not")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.username, user.username) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.active, user.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, name, email, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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




