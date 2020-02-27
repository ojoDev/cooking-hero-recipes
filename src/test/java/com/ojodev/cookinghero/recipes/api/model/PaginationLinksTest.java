package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaginationLinksTest {

    @Test
    public void createPaginationLinksLimit() throws MalformedURLException {
        Integer offset = 20;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getFirst());
        assertEquals(sUrl + "?offset=10&limit=10", paginationLinks.getPrev());
        assertEquals(sUrl + "?offset=20&limit=10", paginationLinks.getSelf());
        assertEquals(sUrl + "?offset=30&limit=10", paginationLinks.getNext());
        assertEquals(sUrl + "?offset=70&limit=10", paginationLinks.getLast());
    }

    @Test
    public void createPaginationLinksOffsetNotMultipleOfLimit() {
        Integer offset = 25;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getFirst());
        assertEquals(sUrl + "?offset=15&limit=10", paginationLinks.getPrev());
        assertEquals(sUrl + "?offset=25&limit=10", paginationLinks.getSelf());
        assertEquals(sUrl + "?offset=35&limit=10", paginationLinks.getNext());
        assertEquals(sUrl + "?offset=75&limit=10", paginationLinks.getLast());
    }

    @Test
    public void createPaginationInfoFirstPage() {
        Integer offset = 0;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getSelf());
        assertEquals(sUrl + "?offset=10&limit=10", paginationLinks.getNext());
        assertEquals(sUrl + "?offset=70&limit=10", paginationLinks.getLast());
    }

    @Test
    public void createPaginationInfoSecondPage() {
        Integer offset = 10;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getFirst());
        assertEquals(sUrl + "?limit=10", paginationLinks.getPrev());
        assertEquals(sUrl + "?offset=10&limit=10", paginationLinks.getSelf());
        assertEquals(sUrl + "?offset=20&limit=10", paginationLinks.getNext());
        assertEquals(sUrl + "?offset=70&limit=10", paginationLinks.getLast());
    }

    @Test
    public void createPaginationInfoPrevLastPage() {
        Integer offset = 65;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getFirst());
        assertEquals(sUrl + "?offset=55&limit=10", paginationLinks.getPrev());
        assertEquals(sUrl + "?offset=65&limit=10", paginationLinks.getSelf());
        assertEquals(sUrl + "?offset=75&limit=10", paginationLinks.getNext());
        assertEquals(sUrl + "?offset=75&limit=10", paginationLinks.getLast());
    }

    @Test
    public void createPaginationInfoLastPage() {
        Integer offset = 70;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationLinks paginationLinks = new PaginationLinks(offset, limit, totalElements, url);
        assertNotNull(paginationLinks);
        assertEquals(sUrl + "?limit=10", paginationLinks.getFirst());
        assertEquals(sUrl + "?offset=60&limit=10", paginationLinks.getPrev());
        assertEquals(sUrl + "?offset=70&limit=10", paginationLinks.getSelf());
        ;
    }

    @Test
    public void testNullURL() {
        Integer offset = 70;
        Integer limit = 10;
        Integer totalElements = 77;
        try {
            new PaginationLinks(offset, limit, totalElements, null);
            fail("Need throws an exception");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }


}
