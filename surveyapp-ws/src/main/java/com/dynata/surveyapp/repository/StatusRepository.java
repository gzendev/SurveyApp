package com.dynata.surveyapp.repository;

import com.dynata.surveyapp.model.Member;
import com.dynata.surveyapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}