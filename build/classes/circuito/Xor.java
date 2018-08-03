/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe Xor
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Xor implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica XOR.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a XOR
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
            return 1;
        } else {
            return 0;
        }
    }

}
