package com.dynata.surveyapp.service;

import com.dynata.surveyapp.model.Member;
import com.dynata.surveyapp.model.Participation;
import java.util.List;

public interface ParticipationService {

    void saveAll(final List<Participation> participations);
}
