package com.meli.mutant.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("count_mutant_dna")
    private float countMutantDna;

    @JsonProperty("count_human_dna")
    private float countHumanDna;

    private float ratio;


    public void setCountMutantDna(float countMutantDna) {
        this.countMutantDna = countMutantDna;
    }


    public void setCountHumanDna(float countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public float getRatio() {
        return countMutantDna/countHumanDna;
    }

}
