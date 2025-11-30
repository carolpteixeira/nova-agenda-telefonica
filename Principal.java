import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        try {
            agenda.recuperarContatos("contatos.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Aviso: Iniciando com agenda vazia ou erro ao ler arquivo: " + e.getMessage());
        }

        do {
            System.out.println("\n--- Menu da Agenda ---");
            System.out.println("1. Inserir contato");
            System.out.println("2. Buscar por Nome");
            System.out.println("3. Buscar por relação");
            System.out.println("4. Alterar contato");
            System.out.println("5. Remover contato");
            System.out.println("6. Listar contatos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("\nNome: ");
                    String nomeInserir = scanner.nextLine();
                    
                    if (agenda.buscarContatoPorNome(nomeInserir) != null) {
                        System.out.println("\nErro: Contato já existe com este nome exato.");
                    } else {
                        System.out.print("Telefone: ");
                        String telefoneInserir = scanner.nextLine();
                        System.out.print("Endereço: ");
                        String enderecoInserir = scanner.nextLine();
                        System.out.print("Relação: ");
                        String relacaoInserir = scanner.nextLine();
                        
                        agenda.inserirContato(new Contato(nomeInserir, telefoneInserir, enderecoInserir, relacaoInserir));
                        System.out.println("\nContato inserido com sucesso!");
                        salvarContatosAutomaticamente(agenda);
                    }
                    break;
                
                case 2: 
                    System.out.print("\nDigite o nome (ou parte dele) para buscar: ");
                    String nomeBuscar = scanner.nextLine();
                    Contato contatoBuscado = agenda.buscarContato(nomeBuscar);
                    
                    if (contatoBuscado != null) {
                        System.out.println("\nContato encontrado: " + contatoBuscado);
                    } else {
                        System.out.println("\nContato não encontrado.");
                    }
                    break;
                case 3: 
                    System.out.print("\nDigite a relação a buscar (ex: Trabalho, Amigo): ");
                    String relacaoBuscar = scanner.nextLine();
                    
                    List<Contato> contatosRelacao = agenda.buscarContatosPorRelacao(relacaoBuscar);
                    
                    if (!contatosRelacao.isEmpty()) {
                        System.out.println("\nContatos encontrados com relação '" + relacaoBuscar + "':");
                        for(Contato c : contatosRelacao) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("\nNenhum contato encontrado com essa relação.");
                    }
                    break;
                
               case 4: 
                    System.out.print("\nDigite o nome do contato que deseja alterar: ");
                    String nomeParaEditar = scanner.nextLine();
                    
        
                    Contato contatoEditar = agenda.buscarContato(nomeParaEditar); 

                    if (contatoEditar == null) {
                        System.out.println("\nContato não encontrado.");
                    } else {
                        System.out.println("\nEditando: " + contatoEditar.getNome());
                        int opcaoEdicao;
                        
                        do {
                            System.out.println("\n--- O que deseja alterar? ---");
                            System.out.println("1. Nome");
                            System.out.println("2. Telefone");
                            System.out.println("3. Endereço");
                            System.out.println("4. Relação");
                            System.out.println("0. Concluir edição e voltar");
                            System.out.print("Opção: ");
                            
                            while (!scanner.hasNextInt()) {
                                scanner.next();
                            }
                            opcaoEdicao = scanner.nextInt();
                            scanner.nextLine();

                            boolean alterou = false;

                            switch (opcaoEdicao) {
                                case 1:
                                    System.out.print("Novo Nome: ");
                                    contatoEditar.setNome(scanner.nextLine());
                                    alterou = true;
                                    break;
                                case 2:
                                    System.out.print("Novo Telefone: ");
                                    contatoEditar.setTelefone(scanner.nextLine());
                                    alterou = true;
                                    break;
                                case 3:
                                    System.out.print("Novo Endereço: ");
                                    contatoEditar.setEndereco(scanner.nextLine());
                                    alterou = true;
                                    break;
                                case 4:
                                    System.out.print("Nova Relação: ");
                                    contatoEditar.setRelacao(scanner.nextLine());
                                    alterou = true;
                                    break;
                                case 0:
                                    System.out.println("Edição finalizada.");
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }

                            if (alterou) {
                                System.out.println("Dado atualizado com sucesso!");
                                salvarContatosAutomaticamente(agenda); // salva por alteração
                            }

                        } while (opcaoEdicao != 0);
                    }
                    break;
                
                case 5: 
                    System.out.print("\nDigite o nome do contato a remover: ");
                    String nomeRemover = scanner.nextLine();
                    if (agenda.removerContato(nomeRemover)){
                        salvarContatosAutomaticamente(agenda);
                        System.out.println("\nContato removido com sucesso!");
                    }                    
                    break;

                case 6: 
                    System.out.println("\nLista de contatos:");
                    agenda.listarContatos();
                    break;
                    
                case 0:
                    System.out.println("\nSaindo...");
                    break;

                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void salvarContatosAutomaticamente(Agenda agenda) {
        try {
            agenda.salvarContatos("contatos.dat");
        } catch (IOException e) {
            System.err.println("\nErro ao salvar contatos: " + e.getMessage());
        }
    }
}