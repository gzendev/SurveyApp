package com.dynata.surveyapp.service.impl;

import com.dynata.surveyapp.dto.StatisticDTO;
import com.dynata.surveyapp.dto.SurveyDTO;
import com.dynata.surveyapp.model.Survey;
import com.dynata.surveyapp.repository.SurveyRepository;
import com.dynata.surveyapp.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaSurveyService implements SurveyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaSurveyService.class);
    @Autowired
    private SurveyRepository repository;

    @Override
    public void saveAll(final List<Survey> surveys) {
        try {
            repository.saveAll(surveys);
        } catch (Exception e) {
            LOGGER.error("Failed to store surveys in database : ", e);
            e.printStackTrace();
        }
    }

    @Override
    public List<SurveyDTO> findCompletedSurveysByMemberId(final Long memberId) {
        return repository.findCompletedSurveysBySurveyId(memberId)
                .stream()
                .map(SurveyDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<SurveyDTO> findPointsByMemberId(final Long memberId) {
        return repository.findPointsByMemberId(memberId)
                .stream()
                .map(SurveyDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<StatisticDTO> findSurveyStatistics() {
        return repository.findSurveyStatistics();
    }
}
