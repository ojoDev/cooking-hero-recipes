package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;


@NodeEntity(label="LanguageName")
public class LanguageNamePO {

    @Id
    @GeneratedValue
    private Long id;

    private String language;

    private String name;

    @Relationship(type="REPRESENTED_BY", direction = Relationship.INCOMING)
    private CuisineTypePO cuisineType;

    public LanguageNamePO() {
    }

    public LanguageNamePO(String language, String name) {
        this.language = language;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CuisineTypePO getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineTypePO cuisineType) {
        this.cuisineType = cuisineType;
    }

}
