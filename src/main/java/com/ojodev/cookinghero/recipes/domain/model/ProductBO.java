package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBO {

    private String id;

    private DescriptiveNameBO name;

    private ProductStatusEnumBO status;


    public ProductBO(String id, DescriptiveNameBO name) {
        this.id = id;
        this.name = name;
        this.status = ProductStatusEnumBO.CREATED_BY_USER;
    }

    public ProductBO(String id, DescriptiveNameBO name, ProductStatusEnumBO status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


}
