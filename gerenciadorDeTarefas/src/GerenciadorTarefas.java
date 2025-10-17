//Aqui vai ficar a parte do CRUD de tarefas

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void verificarArquivoVazio() {
        File arquivo = new File("tarefas.txt");
        if (tarefas.isEmpty() && arquivo.exists()) {
            arquivo.delete();
        }
    }


    public void salvarTarefasEmArquivo(){
        try {
            FileWriter writer = new FileWriter("tarefas.txt");
            for(Tarefa t : tarefas){
                writer.write(t.getDescricao() + ";" + t.isConcluida() + "\n");
            }
            writer.close();

        }catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar as tarefas: " + e.getMessage());
        }
    }

    public boolean excluirTarefaDeArquivo(int indice){
        if(indice >= 0 && indice < tarefas.size()){
            tarefas.remove(indice);
            salvarTarefasEmArquivo();
            return true;
        }
        return false;

    }

    public void carregarDoArquivo(){
        tarefas.clear();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"));
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");
                String descricao = partes[0];
                boolean concluida = Boolean.parseBoolean(partes[1]);
                Tarefa tarefa = new Tarefa(descricao);
                if(concluida){
                    tarefa.marcarComoConcluida();
                }
                tarefas.add(tarefa);
            }
            System.out.println("Tarefas carregadas com sucesso!");
            reader.close();

        }catch (IOException e){
            System.out.println("Ocorreu um erro ao carregar as tarefas: " + e.getMessage());
        }
    }

    public List<Tarefa> getConcluidas() {
    return tarefas.stream()
                  .filter(Tarefa::isConcluida)
                  .toList();
    }

    public List<Tarefa> getPendentes() {
    return tarefas.stream()
                  .filter(t -> !t.isConcluida())
                  .toList();
    }


    public void adicionarTarefa(String descricao) {
        tarefas.add(new Tarefa(descricao));
    }

    public void listarTarefas() {
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i));
            }
    }

    public void marcarComoConcluida(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            if (tarefas.get(indice).isConcluida()) {
                System.out.println("A tarefa já está concluída!");

            } else {
                tarefas.get(indice).marcarComoConcluida();
                System.out.println("\nTarefa marcada como concluída!");
            }

        } else {
            System.out.println("Índice inválido!");
        }
    }

    public void removerTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
            System.out.println("\nTarefa removida com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}
