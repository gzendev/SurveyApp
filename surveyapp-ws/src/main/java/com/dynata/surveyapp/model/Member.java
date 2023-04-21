package com.dynata.surveyapp.model;

import com.dynata.surveyapp.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Member")
public class Member implements Serializable {

    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Integer active;

    @OneToMany(mappedBy = "pk.member", fetch = FetchType.EAGER, cascade =
            CascadeType.ALL, orphanRemoval = true)
    private Set<Participation> participations;

    public Member(MemberDTO dto) {
        if (dto == null) return;
        fullname = dto.getFullname();
        email = dto.getEmail();
        active = dto.getActive();
    }

    public Member(Long id, String fullname, String email, Integer active) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.active = active;
    }
}
