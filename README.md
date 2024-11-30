<h2 align="center"> Teste de Caixa Branca </h2>

<h3 align="center"> Grafo de Fluxo</h3>

<img src="./fluxoDeGrafo.png" alt="Fluxo de Grafo">

<h3>Complexidade Ciclomática</h3>

<p><u>Identificação dos Nós e Arestas</u></p>

Total de Arestas E = 23;
Total de Nós N = 21;
Componentes Conectados P = 1.

<p><u>Cálculo</u></p>

M = E - N + 2.P
M = 23 - 21 + 2.1 = 4

<p><u>Resultado da Complexida Ciclomática</u></p>

A complexidade M desse código é 4.

<h3><u>Caminhos</u></h3>

<li>Caminho 1 (Caminho Verdadeiro):</li>
<p>Esse caminho ocorre quando: <br>
Método conectarBD - Executa o bloco try com sucesso e retorna a conexão.<br>
Método verificarUsuário - Executa o bloco try com sucesso e entra na condição if (rs.next()), login e senha estão corretas, retorna true. E retorna o resultado final antes de fechar o método</p>
<li>Caminho 2 (Caminho Falso):</li>
<p>Esse caminho ocorre quando: <br>
Método conectarBD - Executa o bloco try com sucesso e retorna a conexão.<br>
Método verificarUsuário - Executa o bloco try com sucesso e entra na condição if (rs.next()), login e senha estão incorretos, retorna false. E retorna o resultado final antes de fechar o método</p>
<li>Caminho 3 (Caminho Falso):</li>
<p>Esse caminho ocorre quando: <br>
Método conectarBD - Executa o bloco try sem sucesso -> Ocorre uma exceção então executa o bloco catch -> Retorna a conexão nula ou inválida.<br>
Método verificarUsuário - Executa o bloco try sem sucesso, ocorrendo uma exceção no número 17 do código, ao executar a query. -> Entra no bloco catch -> Retorna o resultado final</p>
<li>Caminho 4 (Caminho Falso):</li>
<p>Esse caminho ocorre quando: <br>
Método conectarBD - Executa o bloco try com sucesso -> Retorna a conexão.<br>
Método verificarUsuário - Executa o bloco try sem sucesso, ocorrendo uma exceção no número 16 do código, ao criar o Statement. -> Entra no bloco catch -> Retorna o resultado final </p>


<p><u>Resumo dos caminhos:</u></p>

<li>Caminho (1): 1; 2; 3; 4; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 17; 18; 19; 22; 23</li>
<li>Caminho (2): 1 - 2 - 3 - 4 - 5 - 7 - 8 - 9 - 10- 11- 12 - 13- 14 - 15 - 16 - 17 - 18 - 22 - 23</li>
<li>Caminho (3): 1; 2; 3; 4; 5; 6; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 17; 20; 21; 22; 23</li>
<li>Caminho (4): 1; 2; 3; 4; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 20; 21; 22; 23</li>
