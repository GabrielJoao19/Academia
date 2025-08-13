import java.util.HashMap;
import java.util.Map;

public class ControleFinanceiro {
    private Map<String, Boolean> statusPagamentoAlunos;

    public ControleFinanceiro() {
        this.statusPagamentoAlunos = new HashMap<>();
    }

    // Métodos para registro e verificação
    public void registrarPagamento(String matriculaAluno) {
        this.statusPagamentoAlunos.put(matriculaAluno, true);
    }

    public boolean verificarPagamento(String matriculaAluno) {
        return this.statusPagamentoAlunos.getOrDefault(matriculaAluno, false);
    }
}