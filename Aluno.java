public class Aluno extends Pessoa {
    private String matricula;
    private PlanoDeTreino planoDeTreino;

    public Aluno(String nome, String cpf, String dataNascimento, String matricula, PlanoDeTreino planoDeTreino) {
        super(nome, cpf, dataNascimento);
        this.matricula = matricula;
        this.planoDeTreino = planoDeTreino;
    }

    // Getters e setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public PlanoDeTreino getPlanoDeTreino() {
        return planoDeTreino;
    }

    public void setPlanoDeTreino(PlanoDeTreino planoDeTreino) {
        this.planoDeTreino = planoDeTreino;
    }
}