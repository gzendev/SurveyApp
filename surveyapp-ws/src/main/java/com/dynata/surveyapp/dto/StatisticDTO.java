package com.dynata.surveyapp.dto;

import lombok.Data;

@Data
public class StatisticDTO {
    private Long surveyId;
    private String surveyName;
    private Long completedParticipants;
    private Long filteredParticipants;
    private Long rejectedParticipants;
    private Double avgTimeSpent;

    public StatisticDTO(Long surveyId, String surveyName, Long completedParticipants, Long filteredParticipants,
                        Long rejectedParticipants, Double avgTimeSpent) {
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.completedParticipants = completedParticipants;
        this.filteredParticipants = filteredParticipants;
        this.rejectedParticipants = rejectedParticipants;
        this.avgTimeSpent = avgTimeSpent;
    }
}
