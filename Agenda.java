import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public Contato buscarContato(String nome) {
        for (Contato contato : contatos) {
            if (contato.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return contato;
            }
        }
        return null;
    }

    public Contato buscarContatoPorNome(String nome) {
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
    }
    public List<Contato> buscarContatosPorRelacao(String relacao) {
    List<Contato> encontrados = new ArrayList<>();
    for (Contato contato : contatos) {

        if (contato.getRelacao().equalsIgnoreCase(relacao)) {
            encontrados.add(contato);
        }
    }
    return encontrados;
}

    public void inserirContato(Contato novoContato) {
       
        if (buscarContatoPorNome(novoContato.getNome()) == null) {
            contatos.add(novoContato);
        }
    }

    public boolean removerContato(String nome) {
 
        boolean removeu = contatos.removeIf(contato -> contato.getNome().equalsIgnoreCase(nome));
        
        if (!removeu) {
            System.out.println("\nContato não encontrado para remoção.");
        }
        return removeu;
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Agenda vazia.");
        } else {
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        }
    }

    public void salvarContatos(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(this);
        }
    }

    public void recuperarContatos(String arquivo) throws IOException, ClassNotFoundException {
        File file = new File(arquivo);
        if (!file.exists()) {
            System.out.println("\nArquivo de contatos não encontrado. Será criado ao salvar.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Agenda agendaRecuperada = (Agenda) ois.readObject();
            this.contatos = agendaRecuperada.getContatos();
        }
    }
}