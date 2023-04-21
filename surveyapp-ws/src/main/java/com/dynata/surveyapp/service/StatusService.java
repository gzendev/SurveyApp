package com.dynata.surveyapp.service;

import com.dynata.surveyapp.model.Status;
import java.util.List;

public interface StatusService {

    void saveAll(final List<Status> statuses);
}
