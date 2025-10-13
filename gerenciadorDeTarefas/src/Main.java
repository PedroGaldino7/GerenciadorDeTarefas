//javac -encoding UTF-8 -d bin src/*.java   || pra compilar
//java -cp bin Main     || pra rodar

//Ou colocar o executar.bat e rodar

import java.util.Scanner;

public class Main {

    public static void limparTela() { 
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausa(Scanner sc) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        sc.nextLine(); 
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        int opcao;

        do {
            limparTela();
            System.out.println("\n=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Marcar tarefa como concluída");
            System.out.println("4. Remover tarefa");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                limparTela();
                    System.out.print("Descrição da tarefa: ");
                    String descricao = sc.nextLine();
                    gerenciador.adicionarTarefa(descricao);
                    System.out.println("\nTarefa adicionada com sucesso!");
                    pausa(sc);
                    break;

                case 2:
                limparTela();
                    System.out.println("=== LISTA DE TAREFAS ===");
                    if (gerenciador.getTarefas().isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                        pausa(sc);
                        break;
                    }else{
                    gerenciador.listarTarefas();
                    pausa(sc);
                    break;
                }

                case 3:
                limparTela();
                    gerenciador.listarTarefas();
                    if (gerenciador.getTarefas().isEmpty()) {
                        System.out.println("Nenhuma tarefa para marcar como concluída.");
                        pausa(sc);
                        break;
                    }else{
                        System.out.print("Número da tarefa para marcar como concluída: ");
                        int indiceConcluir = sc.nextInt() - 1;
                        gerenciador.marcarComoConcluida(indiceConcluir);
                        System.out.println("\nTarefa marcada como concluída!");
                        pausa(sc);
                        break;
                    }

                case 4:
                limparTela();
                    gerenciador.listarTarefas();
                    if (gerenciador.getTarefas().isEmpty()) {
                        System.out.println("Nenhuma tarefa para remover.");
                        pausa(sc);
                    }else{
                        System.out.print("Número da tarefa para remover: ");
                        int indiceRemover = sc.nextInt() - 1;
                        gerenciador.removerTarefa(indiceRemover);
                        System.out.println("\nTarefa removida com sucesso!");
                        pausa(sc);
                        break;
                    }

                case 0:
                limparTela();
                    System.out.println("Encerrando...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
                    pausa(sc);
            }

        } while (opcao != 0);
        sc.close();
    }
}
