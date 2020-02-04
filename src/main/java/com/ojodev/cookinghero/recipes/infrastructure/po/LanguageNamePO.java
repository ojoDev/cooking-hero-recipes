package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Data
@NodeEntity(label="LanguageName")
public class LanguageNamePO {

    @Id
    private Long id;

    private String language;
    private String name;

    public LanguageNamePO(Long id, String language, String name) {
        this.id = id;
        this.language = language;
        this.name = name;
    }

    @Relationship(type="REPRESENTED_BY", direction = Relationship.INCOMING)
    private List<CuisineTypePO> cuisineTypes;

}
