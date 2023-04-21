package com.dynata.surveyapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Status")
public class Status implements Serializable {

    @Id
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Status(final Integer id) {
        this.id = id;
    }

}
