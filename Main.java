import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciando as classes de gerenciamento
        Academia academia = new Academia();
        ControleFinanceiro financeiro = new ControleFinanceiro();
        ControleFrequencia frequencia = new ControleFrequencia();
        Relatorios relatorios = new Relatorios();
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner, academia);
                    break;
                case 2:
                    cadastrarInstrutor(scanner, academia);
                    break;
                case 3:
                    cadastrarPlanoDeTreino(scanner, academia);
                    break;
                case 4:
                    // Chamada corrigida para o método de registro de frequência
                    registrarFrequencia(scanner, frequencia, financeiro, academia);
                    break;
                case 5:
                    registrarPagamento(scanner, financeiro);
                    break;
                case 6:
                    // Chamada corrigida para o método de geração de relatórios
                    gerarRelatorios(scanner, relatorios, academia, frequencia);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gerenciamento de Academia ---");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Instrutor");
        System.out.println("3. Cadastrar Plano de Treino");
        System.out.println("4. Registrar Frequência (Entrada)");
        System.out.println("5. Registrar Pagamento");
        System.out.println("6. Gerar Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno(Scanner scanner, Academia academia) {
        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        // Em um sistema real, você listaria os planos disponíveis
        PlanoDeTreino planoPadrao = new PlanoDeTreino("Plano Padrão", new Instrutor("Instrutor Padrão", "0", "0", "Nenhum"));

        Aluno novoAluno = new Aluno(nome, cpf, dataNascimento, matricula, planoPadrao);
        academia.cadastrarAluno(novoAluno);
        System.out.println("Aluno " + nome + " cadastrado com sucesso!");
    }

    private static void cadastrarInstrutor(Scanner scanner, Academia academia) {
        System.out.println("\n--- Cadastro de Instrutor ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        Instrutor novoInstrutor = new Instrutor(nome, cpf, dataNascimento, especialidade);
        academia.cadastrarInstrutor(novoInstrutor);
        System.out.println("Instrutor " + nome + " cadastrado com sucesso!");
    }

    private static void cadastrarPlanoDeTreino(Scanner scanner, Academia academia) {
        System.out.println("\n--- Cadastro de Plano de Treino ---");
        System.out.print("Nome do Plano: ");
        String nomePlano = scanner.nextLine();

        Instrutor instrutorPadrao = new Instrutor("Sem Nome", "0", "0", "Sem Especialidade");

        PlanoDeTreino novoPlano = new PlanoDeTreino(nomePlano, instrutorPadrao);
        academia.cadastrarPlanoDeTreino(novoPlano);
        System.out.println("Plano de Treino '" + nomePlano + "' cadastrado com sucesso!");
    }

    // Método corrigido para registrar a frequência
    private static void registrarFrequencia(Scanner scanner, ControleFrequencia frequencia, ControleFinanceiro financeiro, Academia academia) {
        System.out.println("\n--- Registro de Frequência ---");
        System.out.print("Matrícula do Aluno: ");
        String matricula = scanner.nextLine();

        // 1. Primeiro, verificar se o aluno existe
        Aluno aluno = academia.buscarAlunoPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("Erro: Aluno com matrícula " + matricula + " não encontrado.");
            return;
        }

        // 2. Depois, verificar se o pagamento está em dia
        if (financeiro.verificarPagamento(matricula)) {
            frequencia.registrarPresenca(matricula);
            System.out.println("Frequência registrada para o aluno " + aluno.getNome() + ".");
        } else {
            System.out.println("Erro: Pagamento pendente. Acesso negado para o aluno " + aluno.getNome() + ".");
        }
    }

    private static void registrarPagamento(Scanner scanner, ControleFinanceiro financeiro) {
        System.out.println("\n--- Registro de Pagamento ---");
        System.out.print("Matrícula do Aluno: ");
        String matricula = scanner.nextLine();

        financeiro.registrarPagamento(matricula);
        System.out.println("Pagamento registrado para a matrícula " + matricula + ".");
    }

    // Método corrigido para gerar relatórios
    private static void gerarRelatorios(Scanner scanner, Relatorios relatorios, Academia academia, ControleFrequencia frequencia) {
        System.out.println("\n--- Geração de Relatórios ---");
        System.out.println("1. Listar Alunos");
        System.out.println("2. Listar Instrutores");
        System.out.println("3. Frequência do Dia");
        System.out.print("Escolha o relatório: ");
        int opcaoRelatorio = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoRelatorio) {
            case 1:
                relatorios.gerarRelatorioAlunos(academia.getAlunos());
                break;
            case 2:
                relatorios.gerarRelatorioInstrutores(academia.getInstrutores());
                break;
            case 3:
                // Passando a instância de Academia para que o relatório possa buscar os nomes
                relatorios.gerarRelatorioFrequencia(frequencia, academia);
                break;
            default:
                System.out.println("Opção de relatório inválida.");
        }
    }
}