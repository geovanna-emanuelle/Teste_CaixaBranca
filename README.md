<h2 align="center"> Teste de Caixa Branca </h2>

<h3>Erros encontrados no código</h3>

Inicialmente não foi possível executar o código, pois ele não inclui um método main, impedindo a execução do programa em Java.
Adicionei um método main para executar o código:

   <pre><code>
 public static void main(String[] args) {
          <br>User user = new User();
          <br>boolean resultado = user.verificarUsuario("lopes", "123");
          <br>System.out.println("Login foi bem sucedido? " + resultado);
    <br>}
    </code></pre>


Após adicionar este método foi possível identificar o que retornou no terminal após rodar o código.

<li>Erro no Driver do MySQL:</li>

<pre><code>Class.forName("com.mysql.Driver.Manager").newInstance();</code></pre>

O código utiliza este driver, porém está incorreto, resultando na falha na conexão com o banco de dados.

<li>Falta de tratamento de exceções</li>

<pre><code>} catch (Exception e) {}</code></pre>

Este bloco try-catch não exibe mensagens de erro, caso ocorre uma falha na conexão com o banco de dados, utilizando o catch dessa forma, não vai ser exibida uma mensagem de erro no console associada a exceção no qual se captura através do "Exception e".
Para corrigir isso, pode ser retornada a mensagem de erro dessa forma:

<pre><code>
  } catch (Exception e) {
    System.out.println("Erro com o banco de dados: " + e.getMessage());
}
</code></pre>

<li>Falta de verificação da conexão com o Banco de Dados</li>

Ao retornar a conexão do banco de dados no código, no final do método conectarBD, não verifica se ela está nula.

<pre>return conn;}</pre>

Seria necessário adicionar um if, para verificar se o conn não está nulo e retornar uma mensagem de erro indicando que houve falha para conectar ao banco de dados.

<pre>public Connection conectarBD() {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); // Driver correto
        String url = "jdbc:mysql://127.0.0.1:3308/test?user=lopes&password=123";
        conn = DriverManager.getConnection(url);
        
        if (conn == null) {
            System.out.println("Falha ao conectar ao banco de dados.");
            return null;
        }
    } catch (Exception e) {
        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    }
    return conn;
}
</pre>

Dessa forma, caso algum erro ocorra, há um retorno no console que ocorreu uma falha, pois sem isso, se torna mais trabalhoso identificar o problema já que não está sendo exibido, seja ele, uma falha com o driver ou url incorreta.



