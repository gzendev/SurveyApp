package com.dynata.surveyapp.service;

import com.dynata.surveyapp.dto.StatisticDTO;
import com.dynata.surveyapp.dto.SurveyDTO;
import com.dynata.surveyapp.model.Survey;
import java.util.List;

public interface SurveyService {

    void saveAll(final List<Survey> surveys);
    List<SurveyDTO> findCompletedSurveysByMemberId(final Long memberId);
    List<SurveyDTO> findPointsByMemberId(final Long memberId);
    List<StatisticDTO> findSurveyStatistics();
}
