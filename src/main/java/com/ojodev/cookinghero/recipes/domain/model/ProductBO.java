package com.ojodev.cookinghero.recipes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBO {

    public ProductBO(String id, DescriptiveNameBO name) {
        this.id = id;
        this.name = name;
        this.status = ProductStatusEnumBO.CREATED_BY_USER;
    }

    private String id;

    private DescriptiveNameBO name;

    private ProductStatusEnumBO status;

}
