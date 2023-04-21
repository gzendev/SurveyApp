package com.dynata.surveyapp.controller;

import com.dynata.surveyapp.dto.StatisticDTO;
import com.dynata.surveyapp.dto.SurveyDTO;
import com.dynata.surveyapp.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    private SurveyService surveyService;

    @GetMapping(path = "/completed-surveys/{memberId}")
    public ResponseEntity<List<SurveyDTO>> getCompletedSurveysByMemberId(@PathVariable final Long memberId) {
        LOGGER.info("GET api/surveys/completed-surveys");
        return ResponseEntity.ok().body(surveyService.findCompletedSurveysByMemberId(memberId));
    }

    @GetMapping(path = "/points-list/{memberId}")
    public ResponseEntity<List<SurveyDTO>> getPointsByMemberId(@PathVariable final Long memberId) {
        LOGGER.info("GET api/surveys/points-list");
        return ResponseEntity.ok().body(surveyService.findPointsByMemberId(memberId));
    }

    @GetMapping(path = "/statistics")
    public ResponseEntity<List<StatisticDTO>> getStatisticsBySurveyId() {
        LOGGER.info("GET api/surveys/statistics");
        return ResponseEntity.ok().body(surveyService.findSurveyStatistics());
    }
}
