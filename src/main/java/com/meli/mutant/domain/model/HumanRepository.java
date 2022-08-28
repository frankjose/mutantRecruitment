package com.meli.mutant.domain.model;

public interface HumanRepository {

    boolean isMutant(String[] dna);
    boolean isValidDna(String[] dna);
}
