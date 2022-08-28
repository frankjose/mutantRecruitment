package com.meli.mutant.domain.model;

public interface DnaRepository {

    boolean isMutant(String[] dna);
    boolean isValidDna(String[] dna);
}
