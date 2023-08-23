public abstract class Pessoa {
    private String nome;
    private String telefone;
    private String endereco;
    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone){this.telefone = telefone;}

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

/*
        se eu for utilizar a classe Endereço, reativar esses get e set
    public Endereco getEndereco() {return endereco;}

    public void setEndereco(Endereco endereco) {this.endereco = endereco;}*/

    public abstract boolean cadastrar();
}
