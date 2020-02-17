package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MeasuresBusinessImpl implements MeasuresBusiness{
    @Override
    public List<MeasureBO> getMeasures(LanguageEnumBO language) {
        return null;
    }

    @Override
    public List<MeasureBO> getMeasures(String name, LanguageEnumBO language) {
        return null;
    }

    @Override
    public Optional<MeasureBO> getMeasure(String measureId, LanguageEnumBO language) {
        return Optional.empty();
    }

    }

   /* @Autowired
    private  MeasuresRepository MeasuresRepository;

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

        checkIfExists(cuisineTypePO);
        checkIfLanguageIsDefault(cuisineType);

        if (existLanguageName(cuisineTypePO,cuisineType)) {
            updateLanguageName(cuisineTypePO,cuisineType);
        } else {
            cuisineTypePO.getNames().add(new LanguageNamePO(cuisineType.getLanguage().toString(), cuisineType.getName()));
        }
        cuisineTypesRepository.save(cuisineTypePO);
    }

    private void checkIfExists(CuisineTypePO cuisineTypePO) throws NotFoundException {
        if (cuisineTypePO == null) {
            throw new NotFoundException();
        }
    }

    private void checkIfLanguageIsDefault(CuisineTypeBO cuisineType) throws ApiFieldsException{
        if (RecipeConstants.DEFAULT_LANGUAGE.equals(cuisineType.getLanguage())) {
            throw new ApiFieldsException(
                    messages.get("error.badrequest.invalidparams.code"),
                    messages.get("error.badrequest.invalidparams.desc"),
                    Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"),
                            HttpHeaders.ACCEPT_LANGUAGE,
                            messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage")))
            );
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
        if (cuisineTypesRepository.findById(cuisineTypeId) == null) {
            throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
        }
        cuisineTypesRepository.deleteById(cuisineTypeId);
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }*/


