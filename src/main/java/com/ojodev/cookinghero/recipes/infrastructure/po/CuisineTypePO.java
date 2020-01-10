package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label="CuisineType")
public class CuisineTypePO {

    @Id
    public String id;

    @Property("name:en")
    public String nameEN;

    @Property("name:es")
    public String nameES;

}
