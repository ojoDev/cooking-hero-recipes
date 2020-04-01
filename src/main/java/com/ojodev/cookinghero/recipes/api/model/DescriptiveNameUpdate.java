package com.ojodev.cookinghero.recipes.api.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;


/**
 * Define a name to describe a item associated with a quantity, in singular or plural form.
 */
@ApiModel(description = "Define a name to describe a item associated with a quantity, in singular or plural form.")
@Validated
public class DescriptiveNameUpdate {

    @JsonProperty("singular")
    @ApiModelProperty(example = "potato", value = "Singular name.", position = 0)
    private Optional<String> singular = Optional.empty();

    @JsonProperty("plural")
    @ApiModelProperty(example = "potatoes", value = "Plural name.", position = 1)
    private Optional<String> plural = Optional.empty();


    public DescriptiveNameUpdate() {
    }

    public DescriptiveNameUpdate(String singular, String plural) {
        this.singular = Optional.ofNullable(singular);
        this.plural = Optional.ofNullable(plural);
    }

    /**
     * singular
     **/

    public String getSingular() {
        return (singular.isPresent() ? singular.get() : null );
    }

    @ApiModelProperty(hidden = true)
    public Optional<String> getSingularOpt() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular == null ? null : Optional.of(singular);
    }


    /**
     * plural
     **/

    public String getPlural() {
        return (plural.isPresent() ? plural.get() : null);
    }

    @ApiModelProperty(hidden = true)
    public Optional<String> getPluralOpt() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural == null ? null : Optional.of(plural);
    }
}
