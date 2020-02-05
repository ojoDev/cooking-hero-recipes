package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label="LanguageName")
public class LanguageNamePO {

    @Id
    private Long id;

    private String language;

    private String name;

    public LanguageNamePO(String language, String name) {
        this.language = language;
        this.name = name;
    }

    @Relationship(type="REPRESENTED_BY", direction = Relationship.INCOMING)
    private CuisineTypePO cuisineType;

}
