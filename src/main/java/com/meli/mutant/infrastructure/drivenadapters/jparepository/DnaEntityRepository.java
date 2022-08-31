package com.meli.mutant.infrastructure.drivenadapters.jparepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DnaEntityRepository {

    @Autowired
    private DnaCrudRepository dnaCrudRepository;

    public DnaEntity save(DnaEntity dna){
         return dnaCrudRepository.save(dna);
    }
}
