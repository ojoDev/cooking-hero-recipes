package com.ojodev.cookinghero.recipes.infrastructure.repository;

import org.junit.runner.RunWith;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesRepositoryTest {

    @TestConfiguration
    static class TestHarnessConfig {

        @Bean
        public Neo4j neo4j() {
            return Neo4jBuilders.newInProcessBuilder()
                    .withDisabledServer() // No need for http
                    .withFixture("CREATE (TheMatrixReloaded:Movie {title:'The Matrix Reloaded', released:2003, tagline:'Free your mind'})")
                    .build();

            // For enterprise use
            // return com.neo4j.harness.EnterpriseNeo4jBuilders.newInProcessBuilder()
            //    .newInProcessBuilder()
            //    .build();
        }
    }

    @Test
    void testSomethingWithTheDriver(@Autowired Driver driver) {
    }
}
