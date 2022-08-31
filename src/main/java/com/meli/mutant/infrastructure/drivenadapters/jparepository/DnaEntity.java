package com.meli.mutant.infrastructure.drivenadapters.jparepository;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Table(name = "dna")
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    int id;

    @Column(name = "dna_value")
    String dnaValue;

    @Column(name = "is_mutant")
    Boolean isMutant;

    public DnaEntity(){}

    public DnaEntity(String dnaValue, Boolean isMutant) {
        this.dnaValue = dnaValue;
        this.isMutant = isMutant;
    }

    public Boolean getIsMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }
}
