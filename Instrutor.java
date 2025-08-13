public class Instrutor extends Pessoa {
    private String especialidade;

    public Instrutor(String nome, String cpf, String dataNascimento, String especialidade) {
        super(nome, cpf, dataNascimento);
        this.especialidade = especialidade;
    }

    // Getters e setters
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}