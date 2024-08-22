package app;

import dao.XDAO;
import model.X;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        XDAO dao = new XDAO(); // Verifique se essa linha está correta
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    dao.listar().forEach(x -> System.out.println(x.getId() + ": " + x.getNome()));
                    break;
                case 2:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine(); // Consome a nova linha
                    X novoX = new X(0, nome, descricao, preco, quantidade);
                    dao.inserir(novoX);
                    break;
                case 3:
                    System.out.print("ID do item a excluir: ");
                    int idExcluir = sc.nextInt();
                    dao.excluir(idExcluir);
                    break;
                case 4:
                    System.out.print("ID do item a atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine(); // Consome a nova linha
                    System.out.print("Nome: ");
                    String nomeAtualizado = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricaoAtualizada = sc.nextLine();
                    System.out.print("Preço: ");
                    double precoAtualizado = sc.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidadeAtualizada = sc.nextInt();
                    sc.nextLine(); // Consome a nova linha
                    X xAtualizado = new X(idAtualizar, nomeAtualizado, descricaoAtualizada, precoAtualizado, quantidadeAtualizada);
                    dao.atualizar(xAtualizado);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
