package com.dynata.surveyapp.repository;

import com.dynata.surveyapp.model.Member;
import com.dynata.surveyapp.model.Participation;
import com.dynata.surveyapp.model.ParticipationPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, ParticipationPk> {

}