package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity(label="LanguageName")
public class DescriptiveNamePO {

    @Id
    @GeneratedValue
    private Long id;

    private String singular;

    private String plural;

    private String language;

    @Relationship(type="REPRESENTED_BY", direction = Relationship.INCOMING)
    private MeasurePO measurePO;

    public DescriptiveNamePO() {
    }

    public DescriptiveNamePO(String singular, String plural, String language) {
        this.singular = singular;
        this.plural = plural;
        this.language = language;

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

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public MeasurePO getMeasurePO() {
        return measurePO;
    }

    public void setMeasurePO(MeasurePO measurePO) {
        this.measurePO = measurePO;
    }
}
