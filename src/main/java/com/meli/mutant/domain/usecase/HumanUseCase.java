package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.HumanRepository;

public class HumanUseCase implements HumanRepository {


    @Override
    public boolean isMutant(String[] dna) {
        return false;
    }
}
