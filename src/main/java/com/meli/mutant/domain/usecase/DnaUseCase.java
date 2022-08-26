package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.DnaRepository;

public class DnaUseCase implements DnaRepository {

    @Override
    public boolean isValidDna(String[] dna) {
        return false;
    }
}
