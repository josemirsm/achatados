
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Início da classe de conexão//

public class Banco {
    public static String status = "Não conectou...";
    public Banco() {
    }
public static java.sql.Connection Conectar() {
        Connection conn = null; //pro compilador ficar feliz
        try {
        // Carrega o driver JDBC 
        String driverName = "com.mysql.jdbc.Driver";   
        Class.forName(driverName); 
        // Configuração da conexão com um banco de dados//
        //troque por seu ip, senha, user, etc
        String serverName = "127.0.0.1:3306";    //caminho do servidor do BD
        String mydatabase ="mydb";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String user = "root";        //nome de um usuário de seu BD      
        String key = "1234";      //sua senha de acesso
        conn = DriverManager.getConnection(url, user, key);         

        //Testa sua conexão// 
        if (conn != null) {
            status = ("Conectado com sucesso!");
        } else {
            status = ("Não foi possivel realizar conexão");
        }
        return conn; 

    } catch (ClassNotFoundException e) {  //Driver não encontrado 
                System.out.println("O driver expecificado nao foi encontrado.");
                return null;
    } catch (SQLException e) {
    //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
      }
}

    public static String statusConection() {
        return status;
    }  


    public static boolean fechaConexao() {
        try {
            Banco.Conectar().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
 
    

    public static java.sql.Connection reiniciaConexao() {
        fechaConexao();
        return Banco.Conectar();
    }
    

}