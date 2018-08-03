/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.ArrayList;

/**
 * Classe Nand
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Nand implements PortaLogica {

    /**
     * operacaoLogica
     * Implementa a operação lógica NAND.
     * 
     * @param entradas ArrayList<>  lista com todas as entradas para a NAND
     * @return int                  valor lógico resultante
     */
    @Override
    public int operacaoLogica(ArrayList<Integer> entradas) {
        for (Integer entrada : entradas) {
            if (entrada == 0) {
                return 1;
            }
        }
        return 0;
    }

}
