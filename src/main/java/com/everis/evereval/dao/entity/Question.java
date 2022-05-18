package com.everis.evereval.dao.entity;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.dao.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Techno techno;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int countdown;
    private double score;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ProposedResponse> proposedResponses = new ArrayList<>();

}
