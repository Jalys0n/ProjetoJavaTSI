import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Implementacao {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        Scanner input = new Scanner(System.in);

        System.out.print("Opcões:\n1 - Cadastrar cliente.\n2 - Consultar.\n3 - Atualizar.\n4 - Remover\n5 - Sair\n");

        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Cadastro");
                System.out.println("Digite o nome: ");
                String nome = input.nextLine();
                cliente.setNome(nome);
                System.out.println("Digite o telefone: ");
                String telefone = input.nextLine();
                cliente.setTelefone(telefone);
                cliente.setData_Cadastro(new Date());
                System.out.println("Digite o endereço: ");
                String endereco = input.nextLine();
                cliente.setEndereco(endereco);
                cliente.setPontuacaoCliente(0);
                System.out.println("Produtos adquiridos até o momento: ");
                String pedidos = input.nextLine();
                cliente.setPedidos(pedidos);
                System.out.println("Sua pontuação começa em 1 por padrão e, conforme compras realizadas, incrementam-se novos valores");
                cliente.setPontuacaoCliente(AutorizacoesCliente.TAXA_PONTUACAO);
                clienteDAO.saveCliente(cliente);
                break; //funciona
            case 2:
                System.out.println("Digite o id desejado: ");
                int iddoselect = input.nextInt();
                List<Cliente> clientesConsulta = clienteDAO.ConsultaClienteId(iddoselect);
                if (!clientesConsulta.isEmpty()) {
                    System.out.println("Cliente(s) encontrado(s):");
                    for (int i = 0; i < clientesConsulta.size(); i++) {
                        Cliente cli = clientesConsulta.get(i);
                        System.out.println("ID: " + cli.getId());
                        System.out.println("Nome: " + cli.getNome());
                        System.out.println("Telefone: " + cli.getTelefone());
                        System.out.println("Endereço: " + cli.getEndereco());
                        System.out.println("Data de Cadastro: " + cli.getData_Cadastro());
                        System.out.println("Pontuação do cliente: " + cli.getPontuacaoCliente());
                        System.out.println("Pedidos: " + cli.getPedidos());
                    }
                } else {
                    System.out.println("Cliente não existe no banco de dados.");
                }
                //ok, o consultar funciona!
                break;
            case 3:
                System.out.println("Atualizacao");
                System.out.println("Digite o id do cliente que você deseja atualizar: ");
                int idAtualizacao = input.nextInt();
                input.nextLine();
                List<Cliente> clientes = clienteDAO.ConsultaClienteId(idAtualizacao);
                if (!clientes.isEmpty()) {
                    Cliente clienteAtualizar = clientes.get(0);
                    System.out.println("Digite o novo nome: ");
                    String novoNome = input.nextLine();
                    clienteAtualizar.setNome(novoNome);

                    System.out.println("Digite o novo telefone: ");
                    String novoTelefone = input.nextLine();
                    clienteAtualizar.setTelefone(novoTelefone);

                    System.out.println("Digite o novo endereço: ");
                    String novoEndereco = input.nextLine();
                    clienteAtualizar.setEndereco(novoEndereco);

                    System.out.println("Pontuação: ");
                    Double pontuacao = input.nextDouble();
                    clienteAtualizar.setPontuacaoCliente(pontuacao);

                    input.nextLine();

                    System.out.println("Pedidos: ");
                    String novosPedidos = input.nextLine();
                    clienteAtualizar.setPedidos(novosPedidos);

                    clienteDAO.updateCliente(clienteAtualizar);

                    System.out.println("Cliente atualizado com sucesso!");
                } else {
                    System.out.println("Cliente não encontrado. Não é possível atualizar.");
                }
                break;//ok, funciona
            case 4:
                System.out.println("Deletar: ");
                System.out.println("Digite o id do cliente que você deseja deletar: ");
                int idDeletar = input.nextInt();
                input.nextLine(); // Limpa o buffer de entrada após a leitura do número

// Consulta o cliente pelo ID
                List<Cliente> clientesDeletar = clienteDAO.ConsultaClienteId(idDeletar);

                if (!clientesDeletar.isEmpty()) {
                    System.out.println("Deseja realmente deletar o cliente " + idDeletar + "?");
                    System.out.println("0 - SIM\n1 - NÃO");
                    int resposta = input.nextInt();

                    if (resposta == 0) {
                        // Deleta o cliente
                        clienteDAO.deletarporId(idDeletar);
                        System.out.println("Cliente deletado com sucesso!");
                    } else if (resposta==1) {
                        System.out.println("O cliente não será deletado.");
                    } else{
                        System.out.println("Essa opção não estava disponível!");
                    }
                }
                //toma cuidadoooooooooooooor eh outro if
                else {
                    System.out.println("Cliente não encontrado no banco de dados.");
                }
                   /* int resposta = JOptionPane.showConfirmDialog(null, "Deseja salvar o arquivo?", "Confirmação",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if(resposta==JOptionPane.YES_OPTION){
                        clienteDAO.deletarporId(iddeletar);
                        System.out.println("Cliente deletado com sucesso!");
                    } else{
                        System.out.println("Cliente do id " + iddeletar + " permanece no sistema.");
                    }*/
                    break;
                    case 5:
                        System.out.println("Saiu");
                        break;//funciona
                    default:
                        System.out.println("Opção não disponível.");
                }

        }
    }

        /*
            Andamento:
            - Cadastro funcionando perfeitamente.
            - Consulta traz o id existe e informa se o id digitado não existe.
            - Update: Informa que não é possível atualizar se o cliente não for encontrado, atualiza!
            - deletar:  afima que não há esse cliente!

         */


