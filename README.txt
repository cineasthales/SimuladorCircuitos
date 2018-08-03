######## Simulador L�gico para Circuitos Combinacionais

Projeto desenvolvido por Isabelle Azevedo e Thales Castro para a disciplina
de Programa��o Orientada a Objetos, ministrada por Felipe Marques no semestre
2017/1 na Universidade Federal de Pelotas.

######## Programa
Fornece ao usu�rio, a partir da leitura de circuitos gerados em arquivos texto 
com formata��o NETBLIF, a visualiza��o da estrutura e da tabela verdade do circuito.
Al�m disso, � poss�vel executar uma simula��o de valores de sa�das de acordo com os
valores de entradas determinados pelo usu�rio.
Toda a intera��o do usu�rio com o programa ocorre por meio de tomadas de decis�o
em janelas pop-up, que tamb�m mostram sa�das. A tabela verdade � igualmente gerada
em um arquivo texto.
Foi desenvolvido utilizando-se Netbeans IDE e JDK.

######## Pr�-requisitos
- Java
- Arquivo texto com nome "circuito.netblif.txt" (descri��o do circuito no formato 
NETBLIF) na mesma pasta do programa
     Exemplo: .inputs i0 i1 i2 i3
	      .outputs s0 s1
	      .gate not a=i1 O=n1
              .gate or2 a=n2 b=n3 O=s0
              .gate and2 a=i0 b=n1 O=n2
              .gate and2 a=i1 b=i2 O=n3
              .gate xor2 a=n3 b=i3 O=s1

######## Funcionamento
Basta executar o arquivo .jar encontrado na pasta "dist" do diret�rio do projeto e seguir
as instru��es do programa.
