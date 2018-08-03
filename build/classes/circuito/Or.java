/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe Or
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Or implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica OR.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a OR
     * @return int                  valor lógico resultante
     */
    @Override
    public int operacaoLogica(ArrayList<Integer> entradas) {
        for (Integer entrada : entradas) {
            if (entrada == 1) {
                return 1;
            }
        }
        return 0;
    }

}
