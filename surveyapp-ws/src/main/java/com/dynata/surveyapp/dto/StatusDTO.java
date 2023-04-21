package com.dynata.surveyapp.dto;

import com.dynata.surveyapp.model.Status;
import lombok.Data;

@Data
public class StatusDTO {

    private String name;

    public StatusDTO(Status model) {
        if (model == null) return;
        name = model.getName();

    }
}
