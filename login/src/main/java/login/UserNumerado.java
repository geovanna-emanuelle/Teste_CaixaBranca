package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserNumerado {

    // **1** - Inicío do método conectarBD
    public Connection conectarBD() {
        // **2** - Declaração da variável conn
        Connection conn = null;
        // **3** - entra no bloco try
        try {
        // **4** - tentativa de carregar o driver e conectar
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1:3308/test?login=lopes&senha=123";
            conn = DriverManager.getConnection(url);
        }
        // **5** - entra no bloco catch
        catch (Exception e) {
            //**6** - retorne uma exceção
        }
        // **7** - retorna a conexão
        return conn;
    } // **8** - fecha o método conectarBD
    
    // **9** - declara a variavel nome
    public String nome = "";
    // **10** - declara a variavel result
    public boolean result = false;

    // **11** - inicio do método verificarUsuario
    public boolean verificarUsuario(String login, String senha) {
        // **12**- declara a variavel sql
        String sql = "";

        // **13** - conexão com o banco de dados
        Connection conn = conectarBD(); 

        //INSTRUÇÃO SQL
        // **14** - instrução SQL
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";

        // **15** - Inicio do bloco try
        try {

            // **16** - cria Statement 
            Statement st = conn.createStatement();
            // **17**  - execução da query
            ResultSet rs = st.executeQuery(sql);

            // **18** - Verifica o resultado da query
            if (rs.next()) {
                // **19** - atualiza o resultado e o nome
                result = true;
                nome = rs.getString("nome");
            }
        } 
         // **20** - inicio do bloco catch
        catch (Exception e) {
            // **21**- retorna algo como tratamento de exceção
        }
        // **22** - retorna o resultado final
        return result;
    }// **23** - fecha o método verificarUsuario
}//fim da class
