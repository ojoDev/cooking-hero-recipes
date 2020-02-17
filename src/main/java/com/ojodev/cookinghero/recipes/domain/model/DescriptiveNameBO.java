package com.ojodev.cookinghero.recipes.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


/**
 * Define a name to describe a item associated with a quantity, in singular or plural form.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptiveNameBO {

    private String singular;

    private String plural;

    private LanguageEnumBO language;

}
