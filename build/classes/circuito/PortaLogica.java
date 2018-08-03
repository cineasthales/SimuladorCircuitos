/**
 * SIMULADOR DE CIRCUITOS
 */

package circuito;

import java.util.ArrayList;

/**
 * Interface PortaLogica
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

interface PortaLogica {
    /**
     * operacaoLogica
     * Declara o método genérico para cálculo de operações lógicas de um circuito.
     * 
     * @param entradas ArrayList<Integer>   lista com todas as entradas para a porta lógica
     * @return int                          valor lógico resultante
     */
    int operacaoLogica(ArrayList<Integer> entradas);
    
}
