package com.dynata.surveyapp.dto;

import com.dynata.surveyapp.model.Participation;
import lombok.Data;

@Data
public class ParticipationDTO {
    private SurveyDTO survey;
    private StatusDTO status;
    private Integer length;

    public ParticipationDTO(Participation model) {
        if (model == null) return;
        length = model.getLength();
        status = new StatusDTO(model.getStatus());
        survey = new SurveyDTO(model.getPk().getSurvey());
    }
}
