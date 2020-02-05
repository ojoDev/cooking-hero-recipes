package com.ojodev.cookinghero.recipes.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentifiableBOTest {

    @Test
    public void createIdentifiableBOWithValidId() {
        String initialId = "ABCabc123";
        IdentifiableBO identifiableBO = new IdentifiableBO(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(initialId, identifiableBO.getId());

        identifiableBO.setId(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(initialId, identifiableBO.getId());
    }

    @Test
    public void createIdentifiableBOWithSpaces() {
        String initialId = "ABC abc 123";
        String normalizedId = "ABC-abc-123";
        IdentifiableBO identifiableBO = new IdentifiableBO(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(normalizedId, identifiableBO.getId());

        identifiableBO.setId(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(normalizedId, identifiableBO.getId());
    }

    @Test
    public void createIdentifiableBOWithStrangeChars() {
        String initialId = "** ** ** A&BC ab#c-12¬¬3  ";
        String normalizedId = "ABC-abc-123";
        IdentifiableBO identifiableBO = new IdentifiableBO(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(normalizedId, identifiableBO.getId());

        identifiableBO.setId(initialId);
        assertNotNull(identifiableBO);
        assertNotNull(identifiableBO.getId());
        assertEquals(normalizedId, identifiableBO.getId());
    }
}
