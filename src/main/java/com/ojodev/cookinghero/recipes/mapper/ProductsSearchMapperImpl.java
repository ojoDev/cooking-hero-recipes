package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.PaginationInfo;
import com.ojodev.cookinghero.recipes.api.model.ProductsSearch;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class ProductsSearchMapperImpl implements ProductsSearchMapper {

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public ProductsSearch toProductsSearch(List<ProductBO> productList, Integer offset, Integer limit, Integer totalProducts, URL url) {
        return new ProductsSearch(productsMapper.toProductsList(productList), new PaginationInfo(offset, limit, totalProducts, url));

    }
}
