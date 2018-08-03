######## Simulador Lógico para Circuitos Combinacionais

Projeto desenvolvido por Isabelle Azevedo e Thales Castro para a disciplina
de Programação Orientada a Objetos, ministrada por Felipe Marques no semestre
2017/1 na Universidade Federal de Pelotas.

######## Programa
Fornece ao usuário, a partir da leitura de circuitos gerados em arquivos texto 
com formatação NETBLIF, a visualização da estrutura e da tabela verdade do circuito.
Além disso, é possível executar uma simulação de valores de saídas de acordo com os
valores de entradas determinados pelo usuário.
Toda a interação do usuário com o programa ocorre por meio de tomadas de decisão
em janelas pop-up, que também mostram saídas. A tabela verdade é igualmente gerada
em um arquivo texto.
Foi desenvolvido utilizando-se Netbeans IDE e JDK.

######## Pré-requisitos
- Java
- Arquivo texto com nome "circuito.netblif.txt" (descrição do circuito no formato 
NETBLIF) na mesma pasta do programa
     Exemplo: .inputs i0 i1 i2 i3
	      .outputs s0 s1
	      .gate not a=i1 O=n1
              .gate or2 a=n2 b=n3 O=s0
              .gate and2 a=i0 b=n1 O=n2
              .gate and2 a=i1 b=i2 O=n3
              .gate xor2 a=n3 b=i3 O=s1

######## Funcionamento
Basta executar o arquivo .jar encontrado na pasta "dist" do diretório do projeto e seguir
as instruções do programa.
