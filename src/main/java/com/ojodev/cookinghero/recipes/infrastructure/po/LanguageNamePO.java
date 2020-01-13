package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label="LanguageName")
public class LanguageNamePO {

    @Id
    public Long id;

    public String language;
    public String name;

}
