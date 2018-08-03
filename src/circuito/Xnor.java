/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe Xnor
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Xnor implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica XNOR.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a XNOR
     * @return int                  valor lógico resultante
     */
    @Override
    public int operacaoLogica(ArrayList<Integer> entradas) {
        int contUns = 0; // contador de 1s
        for (Integer entrada : entradas) {
            if (entrada == 1) {
                contUns++;
            }
        }
        if (contUns % 2 != 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
