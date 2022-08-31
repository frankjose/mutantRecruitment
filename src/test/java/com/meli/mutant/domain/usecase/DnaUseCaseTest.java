package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Dna;
import com.meli.mutant.domain.model.DnaException;
import com.meli.mutant.infrastructure.drivenadapters.jparepository.DnaCrudRepository;
import com.meli.mutant.infrastructure.drivenadapters.jparepository.DnaEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class DnaUseCaseTest {

    @InjectMocks
    private DnaUseCase dnaUseCase;

    @Mock
    private DnaEntityRepository dnaEntityRepository;

    @Mock
    private DnaCrudRepository dnaCrudRepository;

    private Dna dnaModel;

    @BeforeEach
    void setUp() {
        dnaModel = new Dna();
        //dnaUseCase = new DnaUseCase();

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

    @Test
    void saveDnaHandleDnaExceptionTest(){
        String[] dna = {"ATGCTA", "WWWW", "PPPP", "AGAAGG", "CGCATA", "TXACTG"};
        dnaModel.setDna(dna);

        Exception exception = Assertions.assertThrows(DnaException.class, () -> {
            dnaUseCase.checkDna(dnaModel);
        });

        String expectedMessage = "DNA invalido";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void saveDnaIsMutantTrueTest(){
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        dnaModel.setDna(dna);

        ResponseEntity<String> stringResponseEntity = dnaUseCase.checkDna(dnaModel);

        int expectedStatusCode = 200;
        int actualStatusCode = stringResponseEntity.getStatusCode().value();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    void saveDnaIsMutantFalseTest(){
        String[] dna = {"ATGCTA", "CCGTGC", "TTATGT", "AGAAGG", "CGCATA", "TCACTG"};
        dnaModel.setDna(dna);

        ResponseEntity<String> stringResponseEntity = dnaUseCase.checkDna(dnaModel);

        int expectedStatusCode = 403;
        int actualStatusCode = stringResponseEntity.getStatusCode().value();

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }
}