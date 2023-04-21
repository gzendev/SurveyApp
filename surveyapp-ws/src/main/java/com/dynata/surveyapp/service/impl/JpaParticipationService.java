package com.dynata.surveyapp.service.impl;

import com.dynata.surveyapp.model.Participation;
import com.dynata.surveyapp.repository.ParticipationRepository;
import com.dynata.surveyapp.service.ParticipationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JpaParticipationService implements ParticipationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaParticipationService.class);
    @Autowired
    private ParticipationRepository repository;

    @Override
    public void saveAll(final List<Participation> participations) {
        try {
            repository.saveAll(participations);
        } catch (Exception e) {
            LOGGER.error("Failed to store participations in database : ", e);
            e.printStackTrace();
        }
    }
}
