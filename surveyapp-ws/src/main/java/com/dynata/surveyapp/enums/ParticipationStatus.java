package com.dynata.surveyapp.enums;

public enum ParticipationStatus {
    NOT_ASKED(1),
    REJECTED(2),
    FILTERED(3),
    COMPLETED(4);
    private Integer id;
    private ParticipationStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public static ParticipationStatus valueOf(Integer id) {
        for (ParticipationStatus value : values())
            if (value.getId().equals(id))
                return value;
        return null;
    }
}
