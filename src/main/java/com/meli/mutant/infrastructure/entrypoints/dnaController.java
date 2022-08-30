package com.meli.mutant.infrastructure.entrypoints;

import com.meli.mutant.domain.model.Dna;
import com.meli.mutant.domain.model.DnaException;
import com.meli.mutant.domain.usecase.DnaUseCase;
import com.meli.mutant.infrastructure.drivenadapters.jparepository.DnaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dnaController {


    @Autowired
    DnaUseCase dnaUseCase;

    @PostMapping("/mutant")
    public ResponseEntity<String> checkDna(@RequestBody Dna dna){
        try {
           return dnaUseCase.checkDna(dna);
        }catch (DnaException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
