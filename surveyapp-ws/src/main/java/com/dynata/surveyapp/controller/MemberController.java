package com.dynata.surveyapp.controller;

import com.dynata.surveyapp.dto.MemberDTO;
import com.dynata.surveyapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getById(@PathVariable final Long memberId) {
        LOGGER.info("GET api/members/{memberId}");
        return ResponseEntity.ok().body(memberService.findById(memberId));
    }

    @GetMapping(path = "/completed-surveys/{surveyId}")
    public ResponseEntity<List<MemberDTO>> getCompletedSurveysBySurveyId(@PathVariable final Long surveyId) {
        LOGGER.info("GET api/members/completed-surveys/{surveyId}");
        return ResponseEntity.ok().body(memberService.findCompletedSurveysBySurveyId(surveyId));
    }

    @GetMapping(path = "/survey-invitees/{surveyId}")
    public ResponseEntity<List<MemberDTO>> getSurveyInviteesBySurveyId(@PathVariable final Long surveyId) {
        LOGGER.info("GET api/members/survey-invitees/{surveyId}");
        return ResponseEntity.ok().body(memberService.findSurveyInviteesBySurveyId(surveyId));
    }
}
