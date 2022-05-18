package com.everis.evereval.manager.dto;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.dao.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QuestionDTO {

    private Long id;
    private String content;
    private Level level;
    private Techno techno;
    private Type type;
    private int countdown;
    private double score;

    private List<ProposedResponseDTO> proposedResponses = new ArrayList<>();

}
