package com.meli.mutant.infrastructure.drivenadapters.jparepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DnaEntityRepository {

    @Autowired
    private DnaCrudRepository dnaCrudRepository;

    public DnaEntity save(DnaEntity dna){
         return dnaCrudRepository.save(dna);
    }

    public List<DnaEntity> getAll(){
        return (List<DnaEntity>) dnaCrudRepository.findAll();
    }
}
