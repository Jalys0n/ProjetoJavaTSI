import javax.swing.*;
        import java.util.ArrayList;
        import java.util.Date;

public class Cliente extends Pessoa implements AutorizacoesCliente{

    private int Id;
    private Date data_Cadastro;
    private double pontuacaoCliente;

    //era pra ser um array list! mas nao consegui fazer!
    private String pedidos;



    public int getId() {return Id;}
    public void setId(int Id) {this.Id = Id;}

    public Date getData_Cadastro() {return data_Cadastro;}
    public void setData_Cadastro(Date data_Cadastro) {this.data_Cadastro = data_Cadastro;}

    @Override
    public boolean cadastrar() {
        //preciso pensar em uma configuração pra checar se id!=null
        //apenas dados que dizem respeito AO CLIENTE.
        if (getNome() != null && getTelefone() != null && getEndereco() != null) {
            return true;
        } else {
            return false;
        }
    }
    public void testeCadastro(){
        if(cadastrar()!=false){
            JOptionPane.showMessageDialog(null, "Cadastro concluído");
        } else{
            JOptionPane.showMessageDialog(null,"Não foi possível cadastrar, checar informações");
        }
    }

    public double getPontuacaoCliente() {
        return pontuacaoCliente;
    }

    public void setPontuacaoCliente(double pontuacaoCliente) {
        this.pontuacaoCliente = pontuacaoCliente;
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }
}



