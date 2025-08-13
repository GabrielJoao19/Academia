import java.util.ArrayList;
import java.util.List;

public class ControleFrequencia {
    private List<String> presencasHoje;

    public ControleFrequencia() {
        this.presencasHoje = new ArrayList<>();
    }

    public void registrarPresenca(String matriculaAluno) {
        this.presencasHoje.add(matriculaAluno);
    }

    public boolean estaPresente(String matriculaAluno) {
        return this.presencasHoje.contains(matriculaAluno);
    }

    public List<String> getPresencasHoje() {
        return presencasHoje;
    }

    public void resetarFrequenciaDiaria() {
        this.presencasHoje.clear();
    }
}