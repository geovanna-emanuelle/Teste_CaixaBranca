<h2 align="center"> Teste de Caixa Branca </h2>

<h3>Erros encontrados no código</h3>

<li>Falta de main no código</li>

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

Ao retornar a conexão do banco de dados no código, no final do método conectarBD, não retorna uma mensagem de falha alertando o erro se caso a conexão esteje nula.

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

No caso anterior, o erro é referenciado ao retorno para que o programador ou que o usuário saiba como tratar das falhas. Ja o erro indicado abaixo, seria referente ao método verificarUsuário.

<li>Erro no método verificarUsuario</li>

Como não há um if, sendo um verificação se conn está nulo antes de usar, ao tentar acessar as instruções SQL, o método tentará executar essas instruções com uma coneção inválida, sendo ela nula, causando então um NullPointerException. 

A verificação poderia ficar antes das instuções SQL:

<pre><code>    
Connection conn = conectarBD();
   if (conn == null) {
       System.out.println("Conexão com o banco de dados inválida.");
       return false;
   }</code></pre>

<li>Objetos não fechados</li>

Os objetos usados no código, Statement, ResultSet e a conexão, Connection, não são fechados após o uso, como indicado no código abaixo.

<pre> 
   try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {} 
</pre>

Isso pode resultar em várias conexões abertas no banco, fazendo com que o banco atinja seu limite máximo de conexões por permanecerem ocupando memória de forma desnecessária. Para resolver isso, é possível usar o *"finally"* para garantir que esses recursos sejam de fato fechados. O código com o *"finally"* ficaria dessa forma:

<pre>
    try {
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         if (rs.next()) {
            result = true;
             nome = rs.getString("nome");
         }
    } catch (Exception e) {
         //mensagem de erro
    } finally {
         try {
             if (st != null) st.close();
             if (rs != null) rs.close();
             if (conn != null) conn.close();
         } catch (Expection e) {
                System.out.println("Erro ao fechar os recursos: " + e.getMessage());
         }
    }
</pre>

     


