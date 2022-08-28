package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Dna;
import com.meli.mutant.domain.model.DnaRepository;


public class DnaUseCase implements DnaRepository {


    Dna dna;

    DnaUseCase(Dna dna){
        this.dna = dna;
    }

    @Override
    public boolean isMutant(String[] dna) {
        boolean isMutant = false;
        int N = dna[0].length();
        int pivotLimit = N - 3;

        outerLoop:
        for(int x = 0; x < dna.length; x++){
            for(int y = 0; y < dna[x].length(); y++){

                char pivot = dna[x].charAt(y);

                if(y < pivotLimit){
                    if (isEqual(pivot, dna[x].charAt(y + 1), dna[x].charAt(y + 2), dna[x].charAt(y + 3))){
                        isMutant = true;
                    }
                }

                if(x < pivotLimit){
                    if (isEqual(pivot, dna[x + 1].charAt(y), dna[x + 2].charAt(y), dna[x + 3].charAt(y))) {
                        isMutant = true;
                    }
                }

                if (x < pivotLimit && y < pivotLimit) {
                    if (isEqual(pivot, dna[x + 1].charAt(y + 1), dna[x + 2].charAt(y + 2), dna[x + 3].charAt(y + 3))) {
                        isMutant = true;
                    }
                }

                if (x >= 3 && y < pivotLimit) {
                    if (isEqual(pivot, dna[x - 1].charAt(y + 1), dna[x - 2].charAt(y + 2), dna[x - 3].charAt(y + 3))) {
                        isMutant = true;
                    }
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

    boolean isEqual(char a, char b, char c, char d) {
        return (a == b && b == c && c == d);
    }

}
