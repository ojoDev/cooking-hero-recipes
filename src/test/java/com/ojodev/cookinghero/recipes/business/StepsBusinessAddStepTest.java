package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.StepsRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepsBusinessAddStepTest {

    @Autowired
    private StepsBusiness stepsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private StepsRepository stepsRepository;

    @Test
    public void addNewStep() {
        MediaRefBO mediaRef = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);
        StepNewBO stepNewBO = new StepNewBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRef);

        when(this.stepsRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> stepsBusiness.addStep(stepNewBO));

        verify(stepsRepository).save(any(StepPO.class));
    }

}
