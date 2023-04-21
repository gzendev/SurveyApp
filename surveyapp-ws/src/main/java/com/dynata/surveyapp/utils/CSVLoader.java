package com.dynata.surveyapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.dynata.surveyapp.model.*;
import com.dynata.surveyapp.service.MemberService;
import com.dynata.surveyapp.service.ParticipationService;
import com.dynata.surveyapp.service.SurveyService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class CSVLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVLoader.class);
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private ResourceLoader resourceLoader;
    private Resource resource;
    private Map<Long, Member> memberMap;
    private Map<Long, Status> statusMap;
    private Map<Long, Survey> surveyMap;

    private void csvToMembers() {
        this.resource = this.resourceLoader.getResource("classpath:"+"assets/csv/Members.csv");
        LOGGER.debug("Loading Members from Members.csv file");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            this.memberMap = csvParser.getRecords().stream().collect(Collectors.toMap(
                    r -> Long.parseLong(r.get("Member Id")),
                    r -> new Member(Long.parseLong(r.get("Member Id")),
                                    r.get("Full name"),
                                    r.get("E-mail address"),
                                    Integer.parseInt(r.get("Is Active")))));
            this.memberService.saveAll(memberMap.values().stream().toList());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Member CSV file: " + e.getMessage());
        }
    }

    private void csvToStatuses() {
        this.resource = this.resourceLoader.getResource("classpath:"+"assets/csv/Statuses.csv");
        LOGGER.debug("Loading statuses from Statuses.csv file.");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            this.statusMap = csvParser.getRecords().stream().collect(Collectors.toMap(
                    r -> Long.parseLong(r.get("Status Id")),
                    r -> new Status(Integer.parseInt(r.get("Status Id")),
                                    r.get("Name"))
            ));
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Statuses CSV file: " + e.getMessage());
        }
    }

    private void csvToSurveys() {
        this.resource = this.resourceLoader.getResource("classpath:"+"assets/csv/Surveys.csv");
        LOGGER.debug("Loading surveys from Surveys.csv file.");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            this.surveyMap = csvParser.getRecords().stream().collect(Collectors.toMap(
                    r -> Long.parseLong(r.get("Survey Id")),
                    r -> new Survey(Long.parseLong(r.get("Survey Id")),
                                    r.get("Name"),
                                    Integer.parseInt(r.get("Expected completes")),
                                    Integer.parseInt(r.get("Completion points")),
                                    Integer.parseInt(r.get("Filtered points")))
            ));
            this.surveyService.saveAll(surveyMap.values().stream().toList());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    private void csvToParticipation() {
        this.resource = this.resourceLoader.getResource("classpath:"+"assets/csv/Participation.csv");
        LOGGER.debug("Loading surveys from Participation.csv file.");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            this.csvToMembers();
            this.csvToSurveys();
            this.csvToStatuses();

            List<Participation> participations = csvParser.getRecords().stream().map(r -> {
                Integer length = null;
                try {
                    length = Integer.parseInt(r.get("Length"));
                } catch(final NumberFormatException e) {
                    LOGGER.error("Line parsing error on Participation CSV file");
                    length = null;
                }
                Participation participation = new Participation();
                participation.setPk(new ParticipationPk(this.memberMap.get(Long.parseLong(r.get("Member Id"))),
                        this.surveyMap.get(Long.parseLong(r.get("Survey Id")))));
                participation.setStatus(this.statusMap.get(Long.parseLong(r.get("Status"))));
                participation.setLength(length);
                return participation;
            }).collect(Collectors.toList());
            this.participationService.saveAll(participations);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        try {
            csvToParticipation();
        } catch (Exception e) {
            LOGGER.error("Resource not found : ", e);
            e.printStackTrace();
        }
    }

}
