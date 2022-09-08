package com.meli.mutant.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("count_mutant_dna")
    private float countMutantDna;

    @JsonProperty("count_human_dna")
    private float countHumanDna;

    private float ratio = 0;

    public Stats(float countMutantDna, float countHumanDna, float ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }
}
