import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection createConnectiontoMySQL() throws Exception {
        Connection conn = null;

        try {
           //dados do meu banco de dados
            conn = DriverManager.getConnection(url);
        } catch (Exception erro) {
            System.out.println("Erro ao criar conexão: " + erro.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
        // Recuperando conexão com o banco de dados
        Connection con = createConnectiontoMySQL();
        if (con != null) {
            System.out.println("Conexão criada!");
            con.close();
        } else {
            System.out.println("Não foi possível criar a conexão");
        }
    }
}

