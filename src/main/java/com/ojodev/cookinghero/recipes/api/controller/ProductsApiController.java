package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.ProductsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import com.ojodev.cookinghero.recipes.mapper.ProductsMapper;
import com.ojodev.cookinghero.recipes.mapper.ProductsMultipleLanguageMapper;
import com.ojodev.cookinghero.recipes.mapper.ProductsPatchMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "products", value = "Products used in recipes")
public class ProductsApiController implements ProductsApi {


    @Autowired
    private ProductsBusiness productsBusiness;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsMultipleLanguageMapper productsMultiLanguageMapper;

    @Autowired
    private ProductsPatchMapper productsPatchMapper;

    @Autowired
    private LanguageEnumMapper languageEnumMapper;

    @Autowired
    private Messages messages;

    @Autowired
    private RecipesConfig config;


    //TODO DMS: Implementar params order
    public ResponseEntity<ProductsSearch> getProducts(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                                      @ApiParam(value = "Product name, singular or plural.", example = "potato") @Valid @RequestParam(value = "name", required = false) String name,
                                                      @Min(1) @Max(100) @ApiParam(value = "Maximum number of records returned, by default 10.", example = "10") @Valid @RequestParam(value = "limit", required = false) Integer limit,
                                                      @Min(0) @ApiParam(value = "Number of page for skip (pagination).", example = "0") @Valid @RequestParam(value = "offset", required = false) Integer offset) throws ApiException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        List<ProductBO> productsBO = productsBusiness.getProducts(name, language, offset, limit);

      /*  return ResponseEntity.status(HttpStatus.OK)
                .header(org.springframework.http.HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(productsMapper.toProductsSearch(productsBO, ));
*/
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addProduct(@ApiParam(value = "Product info") @Valid @RequestBody ProductNew body) throws ApiException {
        validateBody(body);
        String id = saveProduct(body);
        return ResponseEntity.created(generateLocationHeader(id)).build();
    }

    public ResponseEntity<Product> getProduct(@ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId,
                                              @ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws ApiException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        ProductBO productBO = productsBusiness.getProduct(productId, language).orElseThrow(NotFoundException::new);

        return ResponseEntity.status(HttpStatus.OK)
                .header(org.springframework.http.HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(productsMapper.toProduct(productBO));
    }


    public ResponseEntity<Void> updateProduct(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                              @ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId,
                                              @ApiParam(value = "Product to update.") @Valid @RequestBody ProductUpdate body) throws ApiException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        throwErrorIfProductNotExists(productId);
        Optional<ProductBO> measureOrigin = productsBusiness.getProduct(productId, language);
        if (measureOrigin.isPresent() && measureOrigin.get().getName().getLanguage() == language) {
            ProductBO productPatched = productsPatchMapper.patch(measureOrigin.get(), body);
            productsBusiness.addOrReplaceProduct(productPatched);
        } else {
            productsBusiness.addOrReplaceProduct(productsMapper.toProductBO(body, productId, language));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId) throws NotFoundException {
        productsBusiness.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LanguageEnumBO checkAndExtractAcceptedLanguage(String acceptLanguage) throws ApiFieldsException {

        for (String language : acceptLanguage.split(RecipesConstants.ACCEPT_LANGUAGE_SEPARATOR)) {
            if (LanguageEnum.fromValue(language) != null) {
                return LanguageEnumBO.fromValue(language);
            }
        }
        throw new ApiFieldsException(messages.get("error.badrequest.invalidparams.code"), messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum", org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, LanguageEnum.getValueList()))));
    }

    private void validateBody(ProductNew body) throws ApiException {
        validateDefaultLanguage(body);
        validateInvalidLanguage(body);
    }

    private void validateDefaultLanguage(ProductNew body) throws ApiException {
        boolean existsDefaultLanguage = body.getNames().stream().filter(name -> RecipesConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).count() > 0;
        if (!existsDefaultLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.mustcontaindefault.code"), messages.get("error.badrequest.mustcontaindefault.desc", RecipesConstants.DEFAULT_LANGUAGE));
        }
    }

    private void validateInvalidLanguage(ProductNew body) throws ApiBadRequestException {
        boolean invalidLanguage = body.getNames().stream().filter(name -> name.getLanguage() == null).findAny().isPresent();
        if (invalidLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.invalidlanguage.code"),
                    messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()));
        }
    }

    private void throwErrorIfProductNotExists(String measureId) throws NotFoundException {
        productsBusiness.getProduct(measureId, RecipesConstants.DEFAULT_LANGUAGE).orElseThrow(NotFoundException::new);
    }

    /**
     * Save product
     *
     * @param productNew new product
     * @return id of new product
     * @throws ApiException exception
     */
    private String saveProduct(ProductNew productNew) throws ApiException {
        ProductMultiLanguageBO productMultiLanguageBO = productsMultiLanguageMapper.toProductMultiLanguageBO(productNew, config.getDefaultLanguage());
        productsBusiness.addProduct(productMultiLanguageBO);
        return productMultiLanguageBO.getId();
    }


    private URI generateLocationHeader(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
