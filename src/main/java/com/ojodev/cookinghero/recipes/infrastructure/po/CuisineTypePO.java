package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label="CuisineType")
public class CuisineTypePO {

    @Id
    public String id;

    @Relationship(type = "REPRESENTED_BY")
    public List<LanguageNamePO> names;

}
