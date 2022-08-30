package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Dna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DnaUseCaseTest {



    private DnaUseCase dnaUseCase;

    @BeforeEach
    void setUp() {
        dnaUseCase = new DnaUseCase();
    }

    @Test
    void isMutantTrueTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean isMuntant = dnaUseCase.isMutant(dna);
        Assertions.assertTrue(isMuntant);
    }

    @Test
    void isMutantTrueByHorizontalDnaTest() {
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGAAGG", "CACCTA", "TCAAAA"};
        boolean isMutant = dnaUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByVerticalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATGG", "CACTTA", "TCACTG"};
        boolean isMutant = dnaUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGGTC", "TTATGT", "AGAAGG", "CACCTA", "TCACTG"};
        boolean isMutant = dnaUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByInvertDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATTG", "CACTTA", "TCTCTG"};
        boolean isMutant = dnaUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantFalse(){
        String[] dna = {"ATGCTA", "CCGTGC", "TTATGT", "AGAAGG", "CGCATA", "TCACTG"};
        boolean isMutant = dnaUseCase.isMutant(dna);
        Assertions.assertFalse(isMutant);
    }

    @Test
    void isValidDnaTrueTest(){
        String[] dna = {"ATGCTA", "CCGTGC", "TTATGT", "AGAAGG", "CGCATA", "TCACTG"};
        boolean isValidDna = dnaUseCase.isValidDna(dna);
        Assertions.assertTrue(isValidDna);
    }

    @Test
    void isValidDnaRegExFalseTest(){
        String[] dna = {"ATGCTA", "WWWW", "PPPP", "AGAAGG", "CGCATA", "TXACTG"};

        boolean isValidDna = dnaUseCase.isValidDna(dna);
        Assertions.assertFalse(isValidDna);
    }

    @Test
    void isValidDnaNxNFalseTest(){
        String[] dna = {"ATG", "CGT", "TGT", "AGG"};
        boolean isValidDna = dnaUseCase.isValidDna(dna);
        Assertions.assertFalse(isValidDna);
    }
}