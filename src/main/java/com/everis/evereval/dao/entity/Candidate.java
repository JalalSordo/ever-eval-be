package com.everis.evereval.dao.entity;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Quiz quiz;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mail;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Techno techno;
    private String token;
    private Date tokenExpirationDate;
    private double score;
    private boolean convoked;

}
