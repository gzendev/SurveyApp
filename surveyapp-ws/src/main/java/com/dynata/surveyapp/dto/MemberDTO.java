package com.dynata.surveyapp.dto;

import com.dynata.surveyapp.model.Member;
import lombok.Data;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class MemberDTO {

    private Long id;
    private String fullname;
    private String email;
    private int active;
    private Set<ParticipationDTO> participations;

    public MemberDTO(Member model) {
        if (model == null) return;
        id = model.getId();
        fullname = model.getFullname();
        email = model.getEmail();
        active = model.getActive();
        participations = model.getParticipations().stream()
                .map(ParticipationDTO::new).collect(Collectors.toSet());
    }
}
