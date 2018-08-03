/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

/**
 * Classe Not
 * 
 * @author Isabelle Azevedo
 * @author Thales Castro
 */

public class Not {

    /**
     * operacaoLogica
     * Implementa a operação lógica NOT.
     * 
     * @param entrada int   entrada para a NOT
     * @return int          valor lógico resultante
     */
    public int operacaoLogica(int entrada) {
        if (entrada == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
