package com.dynata.surveyapp.repository;

import com.dynata.surveyapp.dto.StatisticDTO;
import com.dynata.surveyapp.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query(value = "SELECT s FROM Survey s LEFT JOIN Participation p WHERE s.id = p.pk.survey.id " +
            "AND p.status.id = 4 AND p.pk.member.id = ?1")
    List<Survey> findCompletedSurveysBySurveyId(final Long memberId);
    @Query(value = "SELECT s FROM Survey s LEFT JOIN Participation p WHERE s.id = p.pk.survey.id " +
            "AND p.status.id in (3,4) AND p.pk.member.id = ?1")
    List<Survey> findPointsByMemberId(final Long memberId);
    @Query(value = "SELECT new com.dynata.surveyapp.dto.StatisticDTO(s.id, s.name, " +
            "(SELECT COUNT(1) FROM Participation p WHERE p.pk.survey.id = s.id AND p.status = 4 GROUP BY p.status), " +
            "(SELECT COUNT(1) FROM Participation p WHERE p.pk.survey.id = s.id AND p.status = 3 GROUP BY p.status), " +
            "(SELECT COUNT(1) FROM Participation p WHERE p.pk.survey.id = s.id AND p.status = 2 GROUP BY p.status), " +
            "(SELECT AVG(p.length) FROM Participation p WHERE p.pk.survey.id = s.id AND p.length IS NOT NULL)) " +
            "FROM Survey s")
    List<StatisticDTO> findSurveyStatistics();
}