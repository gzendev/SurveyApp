package com.dynata.surveyapp.service.impl;

import com.dynata.surveyapp.dto.MemberDTO;
import com.dynata.surveyapp.exception.SurveyAppException;
import com.dynata.surveyapp.model.Member;
import com.dynata.surveyapp.repository.MemberRepository;
import com.dynata.surveyapp.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaMemberService implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaMemberService.class);
    @Autowired
    private MemberRepository repository;

    @Override
    public void saveAll(final List<Member> members) {
        try {
            repository.saveAll(members);
        } catch (Exception e) {
            LOGGER.error("Failed to store members in database : ", e);
            e.printStackTrace();
        }
    }

    @Override
    public MemberDTO findById(Long memberId) {
        return repository.findById(memberId).map(member -> new MemberDTO(member)).orElseThrow(() -> new SurveyAppException(memberId));
    }

    @Override
    public List<MemberDTO> findCompletedSurveysBySurveyId(final Long surveyId) {
        return repository.findCompletedSurveysBySurveyId(surveyId)
                .stream()
                .map(MemberDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findSurveyInviteesBySurveyId(Long surveyId) {
        return repository.findSurveyInviteesBySurveyId(surveyId)
                .stream()
                .map(MemberDTO::new).collect(Collectors.toList());
    }
}
