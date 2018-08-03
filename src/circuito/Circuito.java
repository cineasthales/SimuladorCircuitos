/**
 * SIMULADOR DE CIRCUITOS
 */
package circuito;

import java.util.*;
import javax.swing.*;

/**
 * Classe Circuito
 *
 * @author Isabelle Azevedo
 * @author Thales Castro
 */
public class Circuito {

    private ArrayList<String> entradas; // lista de entradas do circuito
    private ArrayList<String> saidas; // lista de saídas do circuito
    private Map<String, String> portas; // mapa de saídas (chaves) com portas + entradas (valores)
    private Map<String, String> ordem; // mapa de saídas (chaves) com a ordem de cálculos (valores)

    /**
     * Construtor Constrói a classe Circuito com dados recebidos e aloca espaço
     * para o mapa ordem.
     *
     * @param entradas ArrayList<> lista de entradas do circuito
     * @param saidas ArrayList<> lista de saídas do circuito
     * @param portas Map<String, String> mapa de portas e entradas para cada
     * saída
     */
    public Circuito(ArrayList<String> entradas, ArrayList<String> saidas,
            Map<String, String> portas) {
        this.entradas = entradas;
        this.saidas = saidas;
        this.portas = portas;
        ordem = new HashMap<>();
    }

    // --- GETTERS --- //
    /**
     * getEntradas
     *
     * @return entradas ArrayList<> lista de entradas do circuito
     */
    public ArrayList<String> getEntradas() {
        return entradas;
    }

    /**
     * getSaidas
     *
     * @return entradas ArrayList<> lista de saidas do circuito
     */
    public ArrayList<String> getSaidas() {
        return saidas;
    }

    /**
     * montarOrdem Monta a ordem de cálculos para cada saída.
     */
    public void montarOrdem() {

        String[] dados; // porta e entradas de alguma saída
        Stack pilha = new Stack(); // pilha de verificações de saídasa 
        int i; // contador
        boolean inicial; // é true se for inicial ou se tiver itens já listados

        // percorre todas as saídas do circuito
        for (String saida : saidas) {
            ordem.put(saida, ""); // insere a saída na ordem, com valor vazio
            pilha.push(saida); // empilha a saída
            // enquanto a pulha não esitver vazia
            while (!pilha.empty()) {
                // divide os dados da mapa de portas
                dados = portas.get((String) pilha.peek()).split(" ");
                // inicializa flag
                inicial = true;
                // percorre os dados
                for (i = 1; i < dados.length; i++) {
                    /* se:
                    > o dado não estiver na pilha
                    > o mapa de portas contê-lo como chave
                    > a lista de ordem não contê-lo
                     */
                    if (pilha.search(dados[i]) == -1
                            && portas.containsKey(dados[i])
                            && ordem.get(saida).contains(dados[i]) == false) {
                        // insere na pulha e muda a flag
                        pilha.push(dados[i]);
                        inicial = false;
                    }
                }
                // se for inicial ou tiver itens listados
                if (inicial == true) {
                    // se o mapa de ordem estiver vazio nesta chave, insere;
                    // senão, adiciona com um espaço.
                    if (ordem.get(saida).equals("")) {
                        ordem.put(saida, (String) pilha.peek());
                    } else {
                        ordem.put(saida, ordem.get(saida) + " " + (String) pilha.peek());
                    }
                    // tira da pilha
                    pilha.pop();
                }
            }
        }
    }

    /**
     * simularVetor Simula um resultado com um conjunto de valores de entradas.
     *
     * @param valoresLogicos Map<String, Integer> valores de cada entrada do
     * circuito
     * @return resultado Map<String, Integer> resultado da simulação
     */
    public Map<String, Integer> simularVetor(Map<String, Integer> valoresLogicos) {

        String[] dadosOrdem; // valores divididos do mapa ordem
        String[] dadosPorta; // valores divididos do mapa portas
        ArrayList<Integer> valoresPorta = new ArrayList<>(); // mapa auxiliar
        // resultado da simulação a ser retornado
        Map<String, Integer> resultado = new HashMap<>();
        int i; // contador

        // percorre todas as saídas do circuito
        for (String saida : saidas) {
            // divide os dados de ordem desta saída
            dadosOrdem = ordem.get(saida).split(" ");
            // percorre todos os dados de ordem da saída atual
            for (String dadoOrdem : dadosOrdem) {
                // divide os dados de porta de cada elemento da ordem
                dadosPorta = portas.get(dadoOrdem).split(" ");
                /* se o dado estiver entre as chaves do mapa de parâmetro,
                adiciona no mapa auxiliar valoresPorta */
                for (i = 1; i < dadosPorta.length; i++) {
                    if (valoresLogicos.containsKey(dadosPorta[i])) {
                        valoresPorta.add(valoresLogicos.get(dadosPorta[i]));
                    }
                }
                /* verifica qual porta é, instancia a classe da porta
                correspondente, faz a operação lógica e passa para o mapa
                valoresLogicos */
                if (dadosPorta[0].equalsIgnoreCase("not")) {
                    Not op = new Not();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta.get(0)));
                } else if (dadosPorta[0].equalsIgnoreCase("and")) {
                    And op = new And();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                } else if (dadosPorta[0].equalsIgnoreCase("nand")) {
                    Nand op = new Nand();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                } else if (dadosPorta[0].equalsIgnoreCase("or")) {
                    Or op = new Or();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                } else if (dadosPorta[0].equalsIgnoreCase("nor")) {
                    Nor op = new Nor();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                } else if (dadosPorta[0].equalsIgnoreCase("xor")) {
                    Xor op = new Xor();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                } else if (dadosPorta[0].equalsIgnoreCase("xnor")) {
                    Xnor op = new Xnor();
                    valoresLogicos.put(dadoOrdem, op.operacaoLogica(valoresPorta));
                }
                // limpa o mapa auxiliar
                valoresPorta.clear();
            }
            // adiciona o resultado no mapa de retorno
            resultado.put(saida, valoresLogicos.get(saida));
        }
        // retorna o resultado
        return resultado;
    }

    /**
     * gerarTabelaVerdade Gera a tabela verdade completa do circuito.
     *
     * @return tabelaVerdade int[][] tabela verdade gerada
     */
    public int[][] gerarTabelaVerdade() {
        // determina o número de linhas da tabela verdade
        int combinacoes = (int) Math.pow(2, entradas.size());
        // aloca memória para a tabela verdade
        int tabelaVerdade[][] = new int[combinacoes][entradas.size() + saidas.size()];
        // mapa com os valores a serem calculados, atualizado a cada linha
        Map<String, Integer> valoresLogicos = new HashMap<>();
        // mapa com o resultado de cada linha
        Map<String, Integer> resultado = new HashMap<>();
        int i, j; // contadores
        String binario = ""; // valor binário de cada linha

        // percorre todas as linhas
        for (i = 0; i < combinacoes; i++) {
            // converte de decimal para binário e salva na string
            binario = Integer.toBinaryString(i);
            // adiciona zeros até binário ter o tamanho do número de entradas
            while (binario.length() < entradas.size()) {
                binario = "0" + binario;
            }
            // zera contador de colunas da tabela
            j = 0;
            // percorre todas as entradas
            for (String entrada : entradas) {
                // converte cada caractere do binário para inteiro
                tabelaVerdade[i][j] = Integer.parseInt("" + binario.charAt(j));
                // insere o valor na entrada correspondente
                valoresLogicos.put(entrada, tabelaVerdade[i][j]);
                j++; // incrementa contador de colunas
            }
            // chama o método simularVetor com os dígitos do binário
            resultado = simularVetor(valoresLogicos);
            // percorre todas as saídas
            for (String saida : saidas) {
                // insere na tabela verdade os resultados da simulação
                tabelaVerdade[i][j] = resultado.get(saida);
                j++; // incrementa contador de colunas
            }
            binario = ""; // esvazia string binário para o próximo número
        }

        return tabelaVerdade; // retorna a tabela verdade completa
    }

    /**
     * visualizar Mostra em nova janela a visualização do circuito
     */
    public void visualizar() {

        String[] dadosOrdem; // valores divididos do mapa ordem
        String[] dadosPorta; // valores divididos do mapa portas
        int i; // contador
        int linha = 0, coluna = 0, linhaEntrada, colunaEntrada; // definem posições na janela
        int numColunas = 0; // contador do número de colunas

        // configurações iniciais da tela do circuito
        JFrame tela = new JFrame("Circuito");
        tela.setLayout(null);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // percorre todas as saídas do circuito
        for (String saida : saidas) {
            // divide os dados de ordem desta saída
            dadosOrdem = ordem.get(saida).split(" ");
            // reserva o maior número de dados na ordem
            if (numColunas < dadosOrdem.length) {
                numColunas = dadosOrdem.length;
            }
            // percorre todos os dados de ordem da saída atual
            for (String dadoOrdem : dadosOrdem) {
                // divide os dados de porta de cada elemento da ordem
                dadosPorta = portas.get(dadoOrdem).split(" ");
                // insere imagem correspondente à porta lógica
                if (dadosPorta[0].equalsIgnoreCase("not")) {
                    ImageIcon notImg = new ImageIcon("imagens/not.png");
                    JLabel notLabel = new JLabel(notImg);
                    notLabel.setBounds(coluna, linha, 200, 200);
                    notLabel.setVisible(true);
                    tela.add(notLabel);
                    // insere label da entrada da NOT
                    JLabel entradaLabel = new JLabel(dadosPorta[1]);
                    entradaLabel.setBounds(coluna + 40, linha, 200, 200);
                    entradaLabel.setVisible(true);
                    tela.add(entradaLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("and")) {
                    ImageIcon andImg = new ImageIcon("imagens/and.png");
                    JLabel andLabel = new JLabel(andImg);
                    andLabel.setBounds(coluna, linha, 200, 200);
                    andLabel.setVisible(true);
                    tela.add(andLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("nand")) {
                    ImageIcon nandImg = new ImageIcon("imagens/nand.png");
                    JLabel nandLabel = new JLabel(nandImg);
                    nandLabel.setBounds(coluna, linha, 200, 200);
                    nandLabel.setVisible(true);
                    tela.add(nandLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("or")) {
                    ImageIcon orImg = new ImageIcon("imagens/or.png");
                    JLabel orLabel = new JLabel(orImg);
                    orLabel.setBounds(coluna, linha, 200, 200);;
                    orLabel.setVisible(true);
                    tela.add(orLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("nor")) {
                    ImageIcon norImg = new ImageIcon("imagens/nor.png");
                    JLabel norLabel = new JLabel(norImg);
                    norLabel.setBounds(coluna, linha, 200, 200);
                    norLabel.setVisible(true);
                    tela.add(norLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("xor")) {
                    ImageIcon xorImg = new ImageIcon("imagens/xor.png");
                    JLabel xorLabel = new JLabel(xorImg);
                    xorLabel.setBounds(coluna, linha, 200, 200);
                    xorLabel.setVisible(true);
                    tela.add(xorLabel);
                } else if (dadosPorta[0].equalsIgnoreCase("xnor")) {
                    ImageIcon xnorImg = new ImageIcon("imagens/xnor.png");
                    JLabel xnorLabel = new JLabel(xnorImg);
                    xnorLabel.setBounds(coluna, linha, 200, 200);
                    xnorLabel.setVisible(true);
                    tela.add(xnorLabel);
                }
                // insere labels das entradas das portas lógicas
                if (!dadosPorta[0].equalsIgnoreCase("not")) {
                    linhaEntrada = linha - 20;
                    colunaEntrada = coluna + 30;
                    for (i = 1; i < dadosPorta.length; i++) {
                        JLabel entradaLabel = new JLabel(dadosPorta[i]);
                        entradaLabel.setBounds(colunaEntrada, linhaEntrada, 200, 200);
                        entradaLabel.setVisible(true);
                        tela.add(entradaLabel);
                        linhaEntrada = linhaEntrada + 20;
                    }
                }
                // insere label da saída
                JLabel saidaLabel = new JLabel(dadoOrdem);
                saidaLabel.setBounds(coluna + 155, linha, 200, 200);
                saidaLabel.setVisible(true);
                tela.add(saidaLabel);
                coluna = coluna + 170; // deslocamento de coluna
            }
            coluna = 0; // reinício de coluna
            linha = linha + 130; // deslocamento de linha
        }
        
        // define tamanho da janela de acordo com o tamanho do circuito
        tela.setSize(200 * numColunas, 200 * saidas.size());
        tela.setVisible(true); // torna a tela visível
    }

}
