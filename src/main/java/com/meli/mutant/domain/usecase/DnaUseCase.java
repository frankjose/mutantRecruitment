package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Dna;
import com.meli.mutant.domain.model.DnaException;
import com.meli.mutant.domain.model.DnaRepository;
import com.meli.mutant.domain.model.Stats;
import com.meli.mutant.infrastructure.drivenadapters.jparepository.DnaEntity;
import com.meli.mutant.infrastructure.drivenadapters.jparepository.DnaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class DnaUseCase implements DnaRepository {

    @Autowired
    DnaEntityRepository dnaEntityRepository;

    DnaEntity dnaEntity;

    public DnaUseCase(){ }


    @Override
    public boolean isMutant(String[] dna) {
        boolean isMutant = false;
        int n = dna[0].length();
        int pivotLimit = n - 3;

        outerLoop:
        for(int x = 0; x < dna.length; x++){
            for(int y = 0; y < dna[x].length(); y++){

                char pivot = dna[x].charAt(y);

                if((y < pivotLimit) &&
                    (isEqual(pivot, dna[x].charAt(y + 1), dna[x].charAt(y + 2), dna[x].charAt(y + 3)))){
                        isMutant = true;

                }

                if((x < pivotLimit) &&
                     (isEqual(pivot, dna[x + 1].charAt(y), dna[x + 2].charAt(y), dna[x + 3].charAt(y)))) {
                        isMutant = true;

                }

                if ((x < pivotLimit && y < pivotLimit) &&
                    (isEqual(pivot, dna[x + 1].charAt(y + 1), dna[x + 2].charAt(y + 2), dna[x + 3].charAt(y + 3)))) {
                        isMutant = true;

                }

                if ((x >= 3 && y < pivotLimit) &&
                    (isEqual(pivot, dna[x - 1].charAt(y + 1), dna[x - 2].charAt(y + 2), dna[x - 3].charAt(y + 3)))) {
                        isMutant = true;

                }
                if(isMutant) break outerLoop;
            }


        }


        return isMutant;
    }

    @Override
    public boolean isValidDna(String[] dna) {
        boolean isValidDna = true;

        for(int x = 0; x < dna.length; x++){

            //NxN validation or Min
            if(dna[x].length() < 4 || dna[x].length() != dna.length) {
                isValidDna = false;
                break;
            }
            if (dna[x].matches(".*[^ATCG].*")) {
                isValidDna = false;
                break;
            }
        }

        return isValidDna;
    }

    public ResponseEntity<String> checkDna(Dna dna){
        if(!isValidDna(dna.getDna()))
           throw new DnaException("DNA invalido");

        boolean isMutant = isMutant(dna.getDna());
        dnaEntity = new DnaEntity(dna.getDna()[0], isMutant);
        dnaEntityRepository.save(dnaEntity);

        if(isMutant){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Object> retrieveDnaStats(){

        Stats stats;

        List<DnaEntity> dnaEntities = dnaEntityRepository.getAll();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float countMutantDna = 0;
        float countHumanDna = 0;

        for(DnaEntity dnaEntityRow : dnaEntities){
            if(dnaEntityRow.getIsMutant()) countMutantDna++;

            if(!dnaEntityRow.getIsMutant()) countHumanDna++;
        }
        
        float ratio = (countHumanDna > 0) ? Float.parseFloat(decimalFormat.format(countMutantDna/countHumanDna)) : 0;

        stats = new Stats(
                Float.parseFloat(decimalFormat.format(countMutantDna)),
                Float.parseFloat(decimalFormat.format(countHumanDna)),
                ratio
                );





        return new ResponseEntity<Object>(stats,HttpStatus.OK);



    }

    boolean isEqual(char a, char b, char c, char d) {
        return (a == b && b == c && c == d);
    }


}
