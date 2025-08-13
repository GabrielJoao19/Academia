import java.util.ArrayList;
import java.util.List;

public class PlanoDeTreino {
    private String nome;
    private Instrutor instrutorResponsavel;
    private List<String> exercicios;

    public PlanoDeTreino(String nome, Instrutor instrutorResponsavel) {
        this.nome = nome;
        this.instrutorResponsavel = instrutorResponsavel;
        this.exercicios = new ArrayList<>();
    }

    public void adicionarExercicio(String exercicio) {
        this.exercicios.add(exercicio);
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instrutor getInstrutorResponsavel() {
        return instrutorResponsavel;
    }

    public void setInstrutorResponsavel(Instrutor instrutorResponsavel) {
        this.instrutorResponsavel = instrutorResponsavel;
    }

    public List<String> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<String> exercicios) {
        this.exercicios = exercicios;
    }
}