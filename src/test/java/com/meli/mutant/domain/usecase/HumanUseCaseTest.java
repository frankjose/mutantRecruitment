package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

class HumanUseCaseTest {



    private HumanUseCase humanUseCase;
    private Human human;

    @BeforeEach
    void setUp() {
        human = new Human();
        humanUseCase = new HumanUseCase(human);
    }

    @Test
    void isMutantTrueTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean isMuntant = humanUseCase.isMutant(dna);
        System.out.printf("DNA TEST: %s ", human.getDna());
        Assertions.assertTrue(isMuntant);
    }

    @Test
    void isMutantTrueByHorizontalDnaTest() {
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGAAGG", "CACCTA", "TCAAAA"};
        boolean isMutant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByVerticalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATGG", "CACTTA", "TCACTG"};
        boolean isMutant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGGTC", "TTATGT", "AGAAGG", "CACCTA", "TCACTG"};
        boolean isMutant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantTrueByInvertDiagonalDnaTest(){
        String[] dna = {"ATGCGA", "CCGTTC", "TTATGT", "AGATTG", "CACTTA", "TCTCTG"};
        boolean isMutant = humanUseCase.isMutant(dna);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void isMutantFalse(){
        String[] dna = {"ATGCTA", "CCGTGC", "TTATGT", "AGAAGG", "CGCATA", "TCACTG"};
        boolean isMutant = humanUseCase.isMutant(dna);
        Assertions.assertFalse(isMutant);
    }

    
}