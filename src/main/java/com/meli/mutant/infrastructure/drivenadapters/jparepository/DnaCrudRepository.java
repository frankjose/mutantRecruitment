package com.meli.mutant.infrastructure.drivenadapters.jparepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DnaCrudRepository extends CrudRepository<DnaEntity, Integer> {

    @Query(value = "SELECT COUNT(is_mutant) as mutantCount, is_mutant FROM public.dna WHERE is_mutant = true OR is_mutant = false GROUP BY is_mutant ORDER BY is_mutant DESC", nativeQuery = true)
    List<Object> countTotalDnsByIsMutant();
}
