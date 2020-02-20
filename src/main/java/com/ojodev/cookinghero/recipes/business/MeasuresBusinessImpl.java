package com.ojodev.cookinghero.recipes.business;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.MeasuresRepository;
import com.ojodev.cookinghero.recipes.mapper.DescriptiveNameMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMultipleLanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MeasuresBusinessImpl implements MeasuresBusiness {

    @Autowired
    private MeasuresRepository measuresRepository;

    @Autowired
    private MeasuresMapper measuresMapper;

    @Autowired
    private MeasuresMultipleLanguageMapper measuresMultipleLanguageMapper;

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Autowired
    private Messages messages;

    @Override
    public List<MeasureBO> getMeasures(LanguageEnumBO language) {
        List<MeasurePO> measurePOList = measuresRepository.findAll();
        return measurePOList.stream().map(measure -> measuresMapper.toMeasureBO(measure, setDefaultLanguageIfNull(language))).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public Optional<MeasureBO> getMeasure(String measureId, LanguageEnumBO language) {
        List<MeasurePO> measurePOList = measuresRepository.findByObjectId(measureId);
        return measurePOList == null || measurePOList.isEmpty() ? Optional.empty() : Optional.ofNullable(measuresMapper.toMeasureBO(measurePOList.get(0), language));
    }

    @Override
    public void addMeasure(MeasureMultiLanguageBO newMeasure) throws ApiBadRequestException {
        List<MeasurePO> existentMeasures = measuresRepository.findByObjectId(newMeasure.getId());
        if (existentMeasures != null && !existentMeasures.isEmpty()) {
            throw new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "measure"));
        }
        measuresRepository.save(measuresMultipleLanguageMapper.toMeasurePO(newMeasure));
    }

    @Override
    public void addOrReplaceMeasure(MeasureBO measureBO) throws ApiException {

        List<MeasurePO> existentMeasures = measuresRepository.findByObjectId(measureBO.getId());

        throwErrorIfNotExists(existentMeasures);
        checkIfLanguageIsDefault(measureBO);

        MeasurePO measurePO = existentMeasures.get(0);

        if (existLanguageName(measurePO, measureBO)) {
            updateLanguageName(measurePO, measureBO);
        } else {
            measurePO.getNames().add(descriptiveNameMapper.toDescriptiveNamePO(measureBO.getName()));
        }
        measuresRepository.save(measurePO);
    }

    @Override
    public void deleteMeasure(String measureId) throws NotFoundException {

        throwErrorIfNotExists(measuresRepository.findByObjectId(measureId));

        measuresRepository.deleteById(measureId);
    }


    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }

    private void throwErrorIfNotExists(List<MeasurePO> measures) throws NotFoundException {
        if (measures == null || measures.isEmpty()) {
            throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
        }
    }

    private void checkIfLanguageIsDefault(MeasureBO measure) throws ApiFieldsException {
        if (RecipeConstants.DEFAULT_LANGUAGE == measure.getName().getLanguage()) {
            throw new ApiFieldsException(
                    messages.get("error.badrequest.invalidparams.code"),
                    messages.get("error.badrequest.invalidparams.desc"),
                    Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"),
                            HttpHeaders.ACCEPT_LANGUAGE,
                            messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage")))
            );
        }
    }

    private boolean existLanguageName(MeasurePO measurePO, MeasureBO measureBO) {
        return measurePO.getNames().stream().filter(n -> n.getLanguage().equals(measureBO.getName().getLanguage().toString())).findAny().isPresent();
    }

    private void updateLanguageName(MeasurePO measurePO, MeasureBO measureBO) {
        List<DescriptiveNamePO> newNames = new ArrayList<>();
        for (DescriptiveNamePO name : measurePO.getNames()) {
            if (name.getLanguage().equals(measureBO.getName().getLanguage().toString())) {
                newNames.add(descriptiveNameMapper.toDescriptiveNamePO(measureBO.getName()));
            } else {
                newNames.add(name);
            }
        }
        measurePO.setNames(newNames);
    }


}



