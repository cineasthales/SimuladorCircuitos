/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe And
 *
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class And implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica AND.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a AND
     * @return int                  valor lógico resultante
     */
    @Override
    public int operacaoLogica(ArrayList<Integer> entradas) {
        for (Integer entrada : entradas) {
            if (entrada == 0) {
                return 0;
            }
        }
        return 1;
    }

}
