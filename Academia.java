import java.util.ArrayList;
import java.util.List;

public class Academia {
    private List<Aluno> alunos;
    private List<Instrutor> instrutores;
    private List<PlanoDeTreino> planosDeTreino;

    public Academia() {
        this.alunos = new ArrayList<>();
        this.instrutores = new ArrayList<>();
        this.planosDeTreino = new ArrayList<>();
    }

    // Métodos de cadastro
    public void cadastrarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void cadastrarInstrutor(Instrutor instrutor) {
        this.instrutores.add(instrutor);
    }

    public void cadastrarPlanoDeTreino(PlanoDeTreino plano) {
        this.planosDeTreino.add(plano);
    }

    // Métodos para obter listas
    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Instrutor> getInstrutores() {
        return instrutores;
    }

    // Método para buscar um aluno por matrícula
    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null; // Retorna null se não encontrar
    }
}