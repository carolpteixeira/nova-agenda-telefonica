import java.io.Serializable;

public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String telefone;
    private String endereco;
    private String relacao;

    public Contato(String nome, String telefone, String endereco, String relacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.relacao = relacao;
    }   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRelacao() {
        return relacao;
    }

    public void setRelacao(String relacao) {
        this.relacao = relacao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Telefone: " + telefone + " | Endereço: " + endereco + " | Relação: " + relacao;
    }
}