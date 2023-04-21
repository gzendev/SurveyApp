package com.dynata.surveyapp.dto;

import com.dynata.surveyapp.model.Member;
import com.dynata.surveyapp.model.Survey;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SurveyDTO {

    private String name;
    private Integer expectedCompletes;
    private Integer completionPoints;
    private Integer filteredPoints;

    public SurveyDTO(Survey model) {
        if (model == null) return;
        name = model.getName();
        expectedCompletes = model.getExpectedCompletes();
        completionPoints = model.getCompletionPoints();
        filteredPoints = model.getFilteredPoints();
    }

}
