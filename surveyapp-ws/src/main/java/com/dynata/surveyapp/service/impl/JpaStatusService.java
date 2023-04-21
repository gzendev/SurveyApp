package com.dynata.surveyapp.service.impl;

import com.dynata.surveyapp.model.Status;
import com.dynata.surveyapp.repository.StatusRepository;
import com.dynata.surveyapp.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JpaStatusService implements StatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaStatusService.class);
    @Autowired
    private StatusRepository repository;

    @Override
    public void saveAll(final List<Status> statuses) {
        try {
            repository.saveAll(statuses);
        } catch (Exception e) {
            LOGGER.error("Failed to store statuses in database : ", e);
            e.printStackTrace();
        }
    }
}
