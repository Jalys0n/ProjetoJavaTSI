import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //Crud:
    public boolean saveCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, telefone, endereco, data_cadastro, pontuacaoCliente, pedidos) VALUES (?, ?, ?, ?, ?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Criar conexão com o banco de dados
            conn = ConnectionFactory.createConnectiontoMySQL();

            // Preparar a query com os parâmetros
            pstm = conn.prepareStatement(sql);

            // Definir os valores dos parâmetros
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setString(3, cliente.getEndereco());
            pstm.setDate(4, new java.sql.Date(cliente.getData_Cadastro().getTime()));
            pstm.setDouble(5, cliente.getPontuacaoCliente()* AutorizacoesCliente.TAXA_PONTUACAO);
            pstm.setString(6, cliente.getPedidos());

            // Executar a query
            pstm.execute();

            System.out.println("Cliente salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void updateCliente(Cliente cliente){
        String sql = "Update cliente SET nome = ?,telefone =?, endereco = ?, data_Cadastro = ?, pontuacaoCliente = ?, pedidos = ? where id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;


        try {
            conn = ConnectionFactory.createConnectiontoMySQL();

            pstm =(PreparedStatement)conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2,cliente.getTelefone());
            pstm.setString(3, cliente.getEndereco());
            pstm.setDate(4, new java.sql.Date(cliente.getData_Cadastro().getTime()));
            pstm.setDouble(5, cliente.getPontuacaoCliente());
            pstm.setString(6,cliente.getPedidos());

            pstm.setInt(7, cliente.getId());

            pstm.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
                catch(Exception e) {
                e.printStackTrace();
                }
        }
    }


    public void deletarporId(int id){
        String sql = "delete from cliente where id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.createConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(pstm!=null){
                    pstm.close();
                } if (conn!=null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }




    public List<Cliente> ConsultaClienteId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        List<Cliente> clientes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rset.getInt("id"));
                cliente.setNome(rset.getString("nome"));
                cliente.setTelefone(rset.getString("telefone"));
                cliente.setEndereco(rset.getString("endereco"));
                cliente.setData_Cadastro(rset.getDate("data_Cadastro"));
                cliente.setPontuacaoCliente(rset.getDouble("pontuacaoCliente"));
                cliente.setPedidos(rset.getString("pedidos"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }

}