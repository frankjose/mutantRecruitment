package com.meli.mutant.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class HumanUseCaseTest {



    private HumanUseCase humanUseCase;

    @BeforeEach
    void setUp() {
        humanUseCase = new HumanUseCase();
    }

    @Test
    void isMutantTrueTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }
}