package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe responsável pela autenticação do usuário em um Banco de Dados.
 * 
 * <p>Essa classe fornece método para conectar ao Banco de Dados e verifica as credenciais do usuário no banco.</p>
 * 
 * @author Geovanna
 * @version 1.0
 **/

public class User {

    /**
     * Método responsável pela conexão com o Banco de Dados.
     * 
     * @return A conexão com o Bando de Dados ou retorna null no caso de falha ao conectar.
     **/

    public Connection conectarBD() {
        // Declara a variável que irá armazenar a conexão com o banco de dados.
        Connection conn = null;
        try {
            // Registra o driver JDBC do MySQL.
            Class.forName("com.mysql.Driver.Manager").newInstance();
            // Define a url de conexão ao Banco de Dados.
            String url = "jdbc:mysql://127.0.0.1:3308/test?login=lopes&senha=123";
            // Tenta estabelecer a conexão com o banco de dados utilizando as credenciais fornecidas.
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            //Nenhum tratamento de exceção implementado.
        }
        return conn;
    }

    /** Nome do usuario auenticado. **/
    public String nome = "";
    /** Indica o resultado da verificação do login, sendo true ou false. **/ 
    public boolean result = false;

    /**
     * Método para verificar se o login e senha fornecidos correponde a um usuário válido no banco de dados.
     * 
     * @param login O login do usuário a ser autenticado.
     * @param senha A senha do usuário.
     * @return True se as credenciais do usuário forem válidas e false caso o contrário.
     **/

    public boolean verificarUsuario(String login, String senha) {
        //Declara a variável que armazena a consulta sql.
        String sql = "";
        // Estabele a conexão com o banco de dados através do método conectarBD .
        Connection conn = conectarBD();

        //Monta a consulta SQL
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";

        try {
            //Cria uma instrução para executar a consulta SQL
            Statement st = conn.createStatement();

            //Executa a consulta e processa o resultado da query
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            //Nenhum tratamento de exceção implementado.
        }
        return result;
    }
}//fim da class
