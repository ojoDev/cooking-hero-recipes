package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMapper;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMultipleLanguageMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CuisineTypesBusinessImpl implements CuisineTypesBusiness{

    @Autowired
    private CuisineTypesRepository cuisineTypesRepository;

    @Autowired
    private CuisineTypesMapper cuisineTypesMapper;

    @Autowired
    private CuisineTypesMultipleLanguageMapper cuisineTypesMultipleLanguageMapper;

    @Autowired
    private Messages messages;

    public List<CuisineTypeBO> getCuisineTypes(LanguageEnumBO language) {
        List<CuisineTypePO> cuisineTypePOList = cuisineTypesRepository.findAll();
        return cuisineTypePOList.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, setDefaultLanguageIfNull(language))).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnumBO language) {
        List<CuisineTypePO> cuisineTypesPO;
        if (StringUtils.isEmpty(name)) {
            return getCuisineTypes(language);
        } else {
            cuisineTypesPO = cuisineTypesRepository.findByName(name, language.toString());
        }
        return cuisineTypesPO.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, language)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnumBO language) {
        CuisineTypePO cuisineTypePO = cuisineTypesRepository.findById(id);
        return Optional.ofNullable(cuisineTypesMapper.toCuisineTypeBO(cuisineTypePO, language));
    }

    @Override
    public void addCuisineType(CuisineTypeMultiLanguageBO newCuisineType) throws ApiBadRequestException {
        if (cuisineTypesRepository.findById(newCuisineType.getId()) != null) {
            throw new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"));
        }
        cuisineTypesRepository.save(cuisineTypesMultipleLanguageMapper.toCuisineTypePO(newCuisineType));
    }


    @Override
    public void addOrReplaceCuisineType(CuisineTypeBO cuisineType) throws ApiException {
        CuisineTypePO cuisineTypePO = cuisineTypesRepository.findById(cuisineType.getId());

        throwErrorIfNotExists(cuisineTypePO);

        if (existLanguageName(cuisineTypePO,cuisineType)) {
            updateLanguageName(cuisineTypePO,cuisineType);
        } else {
            cuisineTypePO.getNames().add(new LanguageNamePO(cuisineType.getLanguage().toString(), cuisineType.getName()));
        }
        cuisineTypesRepository.save(cuisineTypePO);
    }

    private void throwErrorIfNotExists(CuisineTypePO cuisineTypePO) throws NotFoundException {
        if (cuisineTypePO == null) {
            throw new NotFoundException(messages.get("error.notfound.cuisinetype.code"), messages.get("error.notfound.cuisinetype.desc"));
        }
    }

    private boolean existLanguageName(CuisineTypePO cuisineTypePO, CuisineTypeBO cuisineType) {
        return cuisineTypePO.getNames().stream().filter(n -> n.getLanguage().equals(cuisineType.getLanguage().toString())).findAny().isPresent();
    }

    private void updateLanguageName(CuisineTypePO cuisineTypePO, CuisineTypeBO cuisineType) {
        cuisineTypePO.getNames().stream().filter(n -> n.getLanguage().equals(cuisineType.getLanguage().toString())).forEach(n -> n.setName(cuisineType.getName()));
    }

    @Override
    public void deleteCuisineType(String cuisineTypeId) throws NotFoundException {
        throwErrorIfNotExists(cuisineTypesRepository.findById(cuisineTypeId));
        cuisineTypesRepository.deleteById(cuisineTypeId);
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipesConstants.DEFAULT_LANGUAGE : language;
    }

}
