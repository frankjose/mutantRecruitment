package com.meli.mutant.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    void isMutantTrueByHorizontalDnaTest() {
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGAAGG", "CACCTA", "TCAAAA"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }

    @Test
    void isMutantTrueByVerticalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATGG", "CACTTA", "TCACTG"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }

    @Test
    void isMutantTrueByDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGGTC", "TTATGT", "AGAAGG", "CACCTA", "TCACTG"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }

    @Test
    void isMutantTrueByInvertDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATTG", "CACTTA", "TCTCTG"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }
}