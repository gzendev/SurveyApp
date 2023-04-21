package com.dynata.surveyapp.repository;

import com.dynata.surveyapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m FROM Member m LEFT JOIN Participation p WHERE m.id = p.pk.member.id AND " +
            "p.status.id = 4 AND p.pk.survey.id = ?1")
    List<Member> findCompletedSurveysBySurveyId(final Long memberId);
    @Query(value = "SELECT m FROM Member m WHERE m.active = 1 AND " +
            "m.id NOT IN (SELECT p.pk.member.id FROM Participation p WHERE p.pk.survey.id = ?1)")
    List<Member> findSurveyInviteesBySurveyId(final Long surveyId);
}