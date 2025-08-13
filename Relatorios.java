import java.util.List;

public class Relatorios {

    public void gerarRelatorioAlunos(List<Aluno> alunos) {
        System.out.println("--- Relatório de Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.println("Matrícula: " + aluno.getMatricula() + " | Nome: " + aluno.getNome() + " | CPF: " + aluno.getCpf());
                System.out.println("Plano: " + aluno.getPlanoDeTreino().getNome());
                System.out.println("---");
            }
        }
    }

    public void gerarRelatorioInstrutores(List<Instrutor> instrutores) {
        System.out.println("--- Relatório de Instrutores ---");
        if (instrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
        } else {
            for (Instrutor instrutor : instrutores) {
                System.out.println("Nome: " + instrutor.getNome() + " | Especialidade: " + instrutor.getEspecialidade() + " | CPF: " + instrutor.getCpf());
                System.out.println("---");
            }
        }
    }

    public void gerarRelatorioFrequencia(ControleFrequencia frequencia, Academia academia) {
        System.out.println("--- Relatório de Frequência do Dia ---");
        List<String> presencas = frequencia.getPresencasHoje();
        if (presencas.isEmpty()) {
            System.out.println("Nenhuma presença registrada hoje.");
        } else {
            for (String matricula : presencas) {
                Aluno aluno = academia.buscarAlunoPorMatricula(matricula);
                if (aluno != null) {
                    System.out.println("Matrícula: " + matricula + " | Nome: " + aluno.getNome());
                } else {
                    System.out.println("Matrícula: " + matricula + " | Aluno não encontrado.");
                }
            }
        }
        System.out.println("---");
    }
}