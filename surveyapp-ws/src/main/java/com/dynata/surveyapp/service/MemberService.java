package com.dynata.surveyapp.service;

import com.dynata.surveyapp.dto.MemberDTO;
import com.dynata.surveyapp.model.Member;
import java.util.List;

public interface MemberService {
    void saveAll(final List<Member> members);

    MemberDTO findById(final Long memberId);
    List<MemberDTO> findCompletedSurveysBySurveyId(final Long surveyId);
    List<MemberDTO> findSurveyInviteesBySurveyId(final Long surveyId);
}
