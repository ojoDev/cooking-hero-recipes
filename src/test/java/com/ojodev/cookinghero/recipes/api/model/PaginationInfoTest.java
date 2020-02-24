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
public class PaginationInfoTest {

    @Test
    public void createPaginationInfoLimit() throws MalformedURLException {
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

        PaginationInfo paginationInfo = new PaginationInfo(offset, limit, totalElements, url);
        assertNotNull(paginationInfo);
        assertEquals(offset, paginationInfo.getOffset());
        assertEquals(limit, paginationInfo.getLimit());
        assertEquals(Integer.valueOf(3), paginationInfo.getPage());
        assertEquals(Integer.valueOf(77), paginationInfo.getTotalElements());
        assertEquals(Integer.valueOf(8), paginationInfo.getTotalPages());
        assertNotNull(paginationInfo.getLinks());
        assertEquals(sUrl+"?limit=10", paginationInfo.getLinks().getFirst());
        assertEquals(sUrl+"?offset=10&limit=10", paginationInfo.getLinks().getPrev());
        assertEquals(sUrl+"?offset=20&limit=10", paginationInfo.getLinks().getSelf());
        assertEquals(sUrl+"?offset=30&limit=10", paginationInfo.getLinks().getNext());
        assertEquals(sUrl+"?offset=70&limit=10", paginationInfo.getLinks().getLast());
    }

    @Test
    public void createPaginationInfoOffsetLimitDown() throws MalformedURLException {
        Integer offset = 19;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }
        PaginationInfo paginationInfo = new PaginationInfo(offset, limit, totalElements, url);
        assertNotNull(paginationInfo);
        assertEquals(offset, paginationInfo.getOffset());
        assertEquals(limit, paginationInfo.getLimit());
        assertEquals(Integer.valueOf(2), paginationInfo.getPage());
        assertEquals(Integer.valueOf(77), paginationInfo.getTotalElements());
        assertEquals(Integer.valueOf(8), paginationInfo.getTotalPages());

    }

    @Test
    public void createPaginationInfoOffsetLimitUp() throws MalformedURLException

    {
        Integer offset = 23;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
           fail("Error in test url");
        }

        PaginationInfo paginationInfo02 = new PaginationInfo(offset, limit, totalElements, url);
        assertNotNull(paginationInfo02);
        assertEquals(offset, paginationInfo02.getOffset());
        assertEquals(limit, paginationInfo02.getLimit());
        assertEquals(Integer.valueOf(3), paginationInfo02.getPage());
        assertEquals(Integer.valueOf(77), paginationInfo02.getTotalElements());
        assertEquals(Integer.valueOf(8), paginationInfo02.getTotalPages());

    }

    @Test
    public void createPaginationInfoOffsetLimitLastPage() throws MalformedURLException {
        Integer offset = 76;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }

        PaginationInfo paginationInfo = new PaginationInfo(offset, limit, totalElements, url);
        assertNotNull(paginationInfo);
        assertEquals(offset, paginationInfo.getOffset());
        assertEquals(limit, paginationInfo.getLimit());
        assertEquals(Integer.valueOf(8), paginationInfo.getPage());
        assertEquals(Integer.valueOf(77), paginationInfo.getTotalElements());
        assertEquals(Integer.valueOf(8), paginationInfo.getTotalPages());
    }


}
