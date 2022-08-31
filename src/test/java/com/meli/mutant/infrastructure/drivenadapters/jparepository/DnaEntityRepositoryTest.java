package com.meli.mutant.infrastructure.drivenadapters.jparepository;

import com.meli.mutant.domain.model.Dna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DnaEntityRepositoryTest {

    private Dna dnaModel;


    @InjectMocks
    private DnaEntityRepository dnaEntityRepository;

    @Mock
    private DnaCrudRepository dnaCrudRepository;

    @BeforeEach
    void setUp() {
        dnaModel = new Dna();
    }

    @Test
    void saveTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        dnaModel.setDna(dna);
        DnaEntity dnaEntity = new DnaEntity(dnaModel.getDna()[0], true);

        when(dnaEntityRepository.save(dnaEntity)).thenReturn(dnaEntity);

        DnaEntity dnaEntityResponse = dnaEntityRepository.save(dnaEntity);
        Assertions.assertNotNull(dnaEntityResponse);

    }
}