package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//TODO DMS Conseguir test Neo4j con BBDD embebida
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class })
@SpringBootTest(classes = RecipesApplication.class)
public class CuisineTypesRepositoryTest {


    @Autowired
    private CuisineTypesRepository cuisineTypesRepository;

   // @Before
   // public void setup() {
   //     cuisineTypesRepository.save(CuisineTypesExamples.CUISINE_TYPE_PO_01);
   //     cuisineTypesRepository.save(CuisineTypesExamples.CUISINE_TYPE_PO_02);
   //     cuisineTypesRepository.save(CuisineTypesExamples.CUISINE_TYPE_PO_03);

   // }

/*    private static ServerControls embeddedDatabaseServer;

    @BeforeAll
    static void startEmbeddedDatabaseServer() {

        embeddedDatabaseServer = TestServerBuilders.newInProcessBuilder()
                .withFixture(""
                        + "CREATE (:Thing {name: 'Thing1', value:1.0})\n"
                        + "CREATE (:Thing {name: 'Thing2', value:2.0})\n"
                        + "CREATE (t:Thing {name: 'Thing42', value:42.0})\n"
                        + "CREATE (r1:RelatedThing {name: 'r1'})\n"
                        + "CREATE (r2:RelatedThing {name: 'r2'})\n"
                        + "CREATE (t) - [:IS_RELATED_TO] -> (r1)\n"
                        + "CREATE (t) - [:IS_RELATED_TO] -> (r2)"
                )
                .newServer();
    }

    @TestConfiguration
    static class Config {
        @Bean
        public org.neo4j.ogm.config.Configuration configuration() {
            startEmbeddedDatabaseServer();
            return new Configuration.Builder()
                    .uri(embeddedDatabaseServer.boltURI().toString())
                    .build();
        }
    }*/




 /*   @AfterAll
    static void stopEmbeddedDatabaseServer() {

        embeddedDatabaseServer.close();
    }*/

   /* @Autowired
    private CuisineTypesRepository repository;

    @Autowired
    private Driver driver;

    @TestConfiguration
    static class TestHarnessConfig {

        @Bean
        public Neo4j neo4j() {
            return Neo4jBuilders.newInProcessBuilder()
                    .withDisabledServer() // No need for http
                    .withFixture("MERGE (c:CuisineType {id: 'italian'})-[:REPRESENTED_BY]->(ln:LanguageName {name:'italian', language:'en'});\n" +
                            "MATCH (c:CuisineType {id: 'italian'}) MERGE (c)-[:REPRESENTED_BY]->(ln:LanguageName {name:'italiana', language:'es'});\n" +
                            "MERGE (c:CuisineType {id: 'japanese'})-[:REPRESENTED_BY]->(ln:LanguageName {name:'japanese', language:'en'});\n" +
                            "MATCH (c:CuisineType {id: 'japanese'}) MERGE (c)-[:REPRESENTED_BY]->(ln:LanguageName {name:'japonesa', language:'es'});\n" +
                            "MERGE (c:CuisineType {id: 'veggie'})-[:REPRESENTED_BY]->(ln:LanguageName {name:'veggie', language:'en'});\n" +
                            "MATCH (c:CuisineType {id: 'veggie'}) MERGE (c)-[:REPRESENTED_BY]->(ln:LanguageName {name:'vegatariana', language:'es'});")
                    .build();

            // For enterprise use
            // return com.neo4j.harness.EnterpriseNeo4jBuilders.newInProcessBuilder()
            //    .newInProcessBuilder()
            //    .build();
        }
    }*/

    @Test
    public void findAll() {
        List<CuisineTypePO> cuisineType = cuisineTypesRepository.findAll();
        assertNotNull(cuisineType);
    }

    @Test
    public void findById() {
        CuisineTypePO cuisineType = cuisineTypesRepository.findById("veggie");
        assertNotNull(cuisineType);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType.getId());
        assertNotNull(cuisineType.getNames());
        assertEquals(2, cuisineType.getNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_EN, cuisineType.getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getNames().get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ES, cuisineType.getNames().get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineType.getNames().get(1).getName());
    }

    @Test
    public void findByName() {
        List<CuisineTypePO> cuisineTypesEn = cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, CuisineTypesExamples.LANGUAGE_EN);
        assertNotNull(cuisineTypesEn);
        assertEquals(1, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertNotNull(cuisineTypesEn.get(0).getNames());
        assertEquals(1, cuisineTypesEn.get(0).getNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_EN, cuisineTypesEn.get(0).getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getNames().get(0).getName());

        List<CuisineTypePO> cuisineTypesEs = cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, CuisineTypesExamples.LANGUAGE_ES);
        assertNotNull(cuisineTypesEs);
        assertEquals(1, cuisineTypesEs.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEs.get(0).getId());
        assertNotNull(cuisineTypesEs.get(0).getNames());
        assertEquals(1, cuisineTypesEs.get(0).getNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_ES, cuisineTypesEs.get(0).getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypesEs.get(0).getNames().get(0).getName());
    }
}
