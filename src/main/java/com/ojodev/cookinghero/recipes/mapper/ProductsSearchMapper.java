package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductsSearch;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;

import java.net.URL;
import java.util.List;

public interface ProductsSearchMapper {

    ProductsSearch toProductsSearch(List<ProductBO> productList, Integer offset, Integer limit, Integer totalProducts, URL url);

}
