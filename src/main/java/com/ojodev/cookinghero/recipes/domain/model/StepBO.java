package com.ojodev.cookinghero.recipes.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ojodev.cookinghero.recipes.api.model.Media;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Step to cook a recipe.
 */
@Data
@NoArgsConstructor
public class StepBO {

    private Integer id;

    private String description;

    private MediaBO media;

    public StepBO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public StepBO(Integer id, String description, MediaBO media) {
        this.id = id;
        this.description = description;
        this.media = media;
    }
}
