/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe Nor
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Nor implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica NOR.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a NOR
     * @return int                  valor lógico resultante
     */
    @Override
    public int operacaoLogica(ArrayList<Integer> entradas) {
        for (Integer entrada : entradas) {
            if (entrada == 1) {
                return 0;
            }
        }
        return 1;
    }
    
}
