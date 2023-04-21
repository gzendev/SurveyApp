package com.dynata.surveyapp.exception;

import com.dynata.surveyapp.message.Messages;

public class SurveyAppException extends RuntimeException {
    private static final long serialVersionUID = -3149207615155492297L;
    private final String message;

    public SurveyAppException(Long id) {
        this.message = String.format("ID not found: %d", id);
    }

    public SurveyAppException(String message) {
        this.message = message;
    }

    public static SurveyAppException notFound() {
        return new SurveyAppException(Messages.ID_NOT_FOUND);
    }

    public String getMessage() {
        return message;
    }
}