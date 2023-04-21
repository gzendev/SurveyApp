package com.dynata.surveyapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Survey")
public class Survey implements Serializable {

    @Id
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "expected_completes")
    private Integer expectedCompletes;

    @Column(name = "completion_points")
    private Integer completionPoints;

    @Column(name = "filtered_points")
    private Integer filteredPoints;

    @OneToMany(mappedBy = "pk.survey", fetch = FetchType.EAGER, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private Set<Participation> participations;

    public Survey(Long id, String name, Integer expectedCompletes, Integer completionPoints, Integer filteredPoints) {
        this.id = id;
        this.name = name;
        this.expectedCompletes = expectedCompletes;
        this.completionPoints = completionPoints;
        this.filteredPoints = filteredPoints;
    }
}
