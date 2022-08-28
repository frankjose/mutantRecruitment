package com.meli.mutant.domain.usecase;

import com.meli.mutant.domain.model.Human;
import com.meli.mutant.domain.model.HumanRepository;


public class HumanUseCase implements HumanRepository {


    Human human;

    HumanUseCase(Human human){
        this.human = human;
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
        return false;
    }

    boolean isEqual(char a, char b, char c, char d) {
        return (a == b && b == c && c == d);
    }

}
