/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Classe Principal
 *
 * @author Isabelle Azevedo
 * @author Thales Castro
 */
public class Principal {

    /**
     * main Instancia circuito lido de arquivo e realiza opções do usuário
     *
     * @param args padrão
     * @throws FileNotFoundException quando não há arquivo
     * @throws IOException quando há erro em entrada ou em saída
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        JOptionPane.showMessageDialog(null, "Simulador de Circuitos v. 1.0", "Bem-vindo!", 1, null);

        // classe Scanner permite leitura de dados do teclado
        Scanner s = new Scanner(System.in);
        // lista para armazenar entradas do circuito do arquivo
        ArrayList<String> entradas = new ArrayList<>();
        // lista para armazenar saídas do circuito do arquivo
        ArrayList<String> saidas = new ArrayList<>();
        // mapa para armazenar portas do circuito do arquivo
        Map<String, String> portas = new HashMap<>();
        // mapa para armazenar valores lógicos das entradas do circuito
        Map<String, Integer> valoresLogicos = new HashMap<>();
        // mapa para armazenar resultado de simulação
        Map<String, Integer> resultado;
        // vetor para aramazenar dados de uma linha do arquivo
        String[] dados;
        // vetor com as opções de valores para simulação
        String[] opcaoBit = {"0", "1"};
        // vetor com as opções de menu
        String[] opcaoMenu = {"Simular Vetor", "Montar Tabela Verdade", "Visualizar Circuito", "Sair"};
        // string auxiliar para informar resultado de simulação e tabela verdade
        String simulacao = "";
        // contadores e auxiliares
        int i, j, opcao, bit;
        // flag de verificação da abertura da janela de visualização do circuito
        boolean aberto = false;

        // leitura do arquivo NETBLIF
        BufferedReader bf = new BufferedReader(new FileReader("circuito.netblif.txt"));

        // lê a primeira linha
        String linha = bf.readLine();
        // enquanto não for linha vazia
        while (linha != null) {
            switch (linha.charAt(1)) {
                // input
                case 'i':
                    dados = linha.split(" "); // divide os dados da linha
                    // insere todas as entradas na lista de entradas
                    for (i = 1; i < dados.length; i++) {
                        entradas.add(dados[i]);
                    }
                    break;
                // output
                case 'o':
                    dados = linha.split(" "); // divide os dados da linha
                    // insere todas as saídas na lista de saídas
                    for (i = 1; i < dados.length; i++) {
                        saidas.add(dados[i]);
                    }
                    break;
                // gate
                case 'g':
                    dados = linha.split(" "); // divide os dados da linha
                    // elimina a numeração junto à porta lógica (exceto not)
                    if (dados[1].contains("nand")) {
                        dados[1] = "nand";
                    } else if (dados[1].contains("and")) {
                        dados[1] = "and";
                    } else if (dados[1].contains("xnor")) {
                        dados[1] = "xnor";
                    } else if (dados[1].contains("xor")) {
                        dados[1] = "xor";
                    } else if (dados[1].contains("nor")) {
                        dados[1] = "nor";
                    } else if (dados[1].contains("or")) {
                        dados[1] = "or";
                    }
                    // percorre o vetor de dados após a porta lógica
                    for (i = 2; i < dados.length; i++) {
                        // percorre a string com o dado corrente
                        for (j = 0; j < dados[i].length(); j++) {
                            /* se encontrar o sinal de igual, eliminar 
                            tudo da string até o sinal*/
                            if (dados[i].charAt(j) == '=') {
                                dados[i] = dados[i].substring(j + 1);
                                /* se não for o último dado, inseri-lo 
                                junto à porta lógica */
                                if (i < dados.length - 1) {
                                    dados[1] = dados[1] + " " + dados[i];
                                }
                                break;
                            }
                        }
                    }
                    /* inserir no mapa as saídas como chaves e as portas
                    + entradas como valores */
                    portas.put(dados[dados.length - 1], dados[1]);
                    break;
            }
            // lê a próxima linha
            linha = bf.readLine();
        }

        // instancia um novo circuito e monta a ordem de cálculos de cada saída
        Circuito c = new Circuito(entradas, saidas, portas);
        c.montarOrdem();

        JOptionPane.showMessageDialog(null, "Circuito carregado do arquivo texto com êxito.", "circuito.netblif.txt", 1, null);

        // menu de opções para o usuário
        do {
            // guarda opção digitada
            opcao = JOptionPane.showOptionDialog(null, "Selecione uma das opções abaixo.",
                    "Menu", -1, 3, null, opcaoMenu, opcaoMenu[3]);

            switch (opcao) {
                // Simular Vetor
                case 0:
                    // usuário fornece valores binários das entradas     
                    for (String entrada : c.getEntradas()) {
                        bit = JOptionPane.showOptionDialog(null, "Selecione o valor da entrada " + entrada + ".",
                                "Simular Vetor", -1, 3, null, opcaoBit, opcaoBit[0]);
                        valoresLogicos.put(entrada, bit);
                    }
                    // faz a simulação com valores fornecidos
                    resultado = c.simularVetor(valoresLogicos);
                    // informa valores binários de saída
                    for (String saida : c.getSaidas()) {
                        simulacao = simulacao + "Valor da saída " + saida + ": " + resultado.get(saida) + "\n";
                    }
                    JOptionPane.showMessageDialog(null, simulacao, "Resultado da Simulação", 1, null); // janela com o resultado da simulação
                    simulacao = ""; // esvazia string auxiliar
                    break;
                // Montar Tabela Verdade
                case 1:
                    // instancia classes para gravar em arquivo
                    FileWriter arq = new FileWriter("tabela_verdade.txt");
                    PrintWriter gravarArq = new PrintWriter(arq);
                    // gera a tabela verdade do circuito
                    int tabelaVerdade[][] = c.gerarTabelaVerdade();
                    // formata a tabela verdade para impressão no arquivo e na janela
                    gravarArq.printf("--- TABELA VERDADE ---%n%n");
                    for (String entrada : c.getEntradas()) {
                        simulacao = simulacao + entrada + "\t";
                        gravarArq.printf(entrada + "\t");
                    }
                    j = 0;
                    for (String saida : c.getSaidas()) {
                        if (j < c.getSaidas().size() - 1) {
                            simulacao = simulacao + saida + "\t";
                            gravarArq.printf(saida + "\t");
                        } else {
                            simulacao = simulacao + saida;
                            gravarArq.printf(saida);
                        }
                        j++;
                    }
                    simulacao = simulacao + "\n\n";
                    gravarArq.printf("%n%n");
                    for (i = 0; i < Math.pow(2, c.getEntradas().size()); i++) {
                        for (j = 0; j < c.getEntradas().size() + c.getSaidas().size(); j++) {
                            if (j < c.getEntradas().size() + c.getSaidas().size() - 1) {
                                simulacao = simulacao + tabelaVerdade[i][j] + "\t";
                                gravarArq.printf(tabelaVerdade[i][j] + "\t");
                            } else {
                                simulacao = simulacao + tabelaVerdade[i][j];
                                gravarArq.printf(tabelaVerdade[i][j] + "");
                            }
                        }
                        simulacao = simulacao + "\n";
                        gravarArq.printf("%n");
                    }
                    arq.close(); // fecha o arquivo
                    JOptionPane.showMessageDialog(null, new JTextArea(simulacao), "Tabela Verdade", -1, null); // janela com a tabela verdade
                    JOptionPane.showMessageDialog(null, "Tabela verdade gravada com êxito no arquivo.", "tabela_verdade.txt", 1, null);
                    simulacao = ""; // esvazia string auxiliar
                    break;
                // Visualizar Circuito 
                case 2:
                    // se já estiver aberta a janela, não abre novamente
                    if (aberto == false ) {
                        c.visualizar();
                        aberto = true;
                    } else {
                       JOptionPane.showMessageDialog(null, "Visualização do circuito já gerada.", "Erro", 0, null);
                    }                                      
                    break;
                // Sair
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo do programa.", "Sair", 2, null);                    
                    break;
                // opção inválida
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", 0, null);
            }

        } while (opcao != 3);
        
        // garante o fechamento de todas as janelas
        System.exit(0);
    }

}
