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
                    criarEAtrubuirPlanoDeTreino(scanner, academia);
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
        System.out.println("3. Criar e Atribuir Plano de Treino");
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
        

        Aluno novoAluno = new Aluno(nome, cpf, dataNascimento, matricula);
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

    private static void criarEAtrubuirPlanoDeTreino(Scanner scanner, Academia academia) {
        System.out.println("\n--- Criação e Atribuição de Plano de Treino ---");

        // 1. Selecionar o Aluno para o qual o treino será criado
        if (academia.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado. Por favor, cadastre um aluno primeiro.");
            return;
        }
        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < academia.getAlunos().size(); i++) {
            System.out.println((i + 1) + ". " + academia.getAlunos().get(i).getNome() + " (Matrícula: " + academia.getAlunos().get(i).getMatricula() + ")");
        }
        System.out.print("Escolha o número do aluno para o qual o plano será criado: ");
        int indiceAluno = scanner.nextInt();
        scanner.nextLine();

        Aluno alunoSelecionado;
        try {
            alunoSelecionado = academia.getAlunos().get(indiceAluno - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Opção de aluno inválida. Retornando ao menu principal.");
            return;
        }

        // 2. Selecionar o Instrutor responsável pela criação
        if (academia.getInstrutores().isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado. Por favor, cadastre um instrutor primeiro.");
            return;
        }
        System.out.println("\nInstrutores disponíveis:");
        for (int i = 0; i < academia.getInstrutores().size(); i++) {
            Instrutor instrutor = academia.getInstrutores().get(i);
            System.out.println((i + 1) + ". " + instrutor.getNome() + " (" + instrutor.getEspecialidade() + ")");
        }
        System.out.print("Escolha o número do instrutor responsável: ");
        int indiceInstrutor = scanner.nextInt();
        scanner.nextLine();

        Instrutor instrutorResponsavel = null;
        try {
            instrutorResponsavel = academia.getInstrutores().get(indiceInstrutor - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Opção de instrutor inválida. Retornando ao menu principal.");
            return;
        }
        
        // 3. Criar o Plano de Treino
        System.out.println("\n--- Criando o Plano de Treino ---");
        System.out.print("Digite o nome do plano de treino (ex: Treino ABC): ");
        String nomePlano = scanner.nextLine();
        
        PlanoDeTreino novoPlano = new PlanoDeTreino(nomePlano, instrutorResponsavel);

        // 4. Adicionar os Exercícios
        System.out.println("\n--- Adicionando Exercícios ---");
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do exercício (ex: Agachamento): ");
            String nomeExercicio = scanner.nextLine();
            System.out.print("Digite os detalhes do exercício (ex: 3 séries de 10 repetições): ");
            String detalhes = scanner.nextLine();
            
            String exercicioCompleto = nomeExercicio + " - " + detalhes;
            novoPlano.adicionarExercicio(exercicioCompleto);

            System.out.print("Deseja adicionar outro exercício? (s/n): ");
            continuar = scanner.nextLine();
        }

        // 5. Atribuir o Plano ao Aluno
        alunoSelecionado.setPlanoDeTreino(novoPlano);
        System.out.println("\nPlano de Treino '" + novoPlano.getNome() + "' criado e atribuído com sucesso ao aluno " + alunoSelecionado.getNome() + ".");
        
        // A função de cadastro de plano na academia não é mais necessária, mas se você
        // quiser manter o registro de todos os planos criados, você pode descomentar
        // a linha abaixo. Caso contrário, o plano existirá apenas no objeto Aluno.
        // academia.cadastrarPlanoDeTreino(novoPlano); 
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
        System.out.println("4. Ficha de Treino de um Aluno"); // << NOVA OPÇÃO
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
                relatorios.gerarRelatorioFrequencia(frequencia, academia);
                break;
            case 4:
                // Implementa a nova funcionalidade aqui
                System.out.print("Digite a matrícula do aluno para ver a ficha de treino: ");
                String matricula = scanner.nextLine();
                Aluno aluno = academia.buscarAlunoPorMatricula(matricula);
                
                if (aluno != null) {
                    relatorios.exibirFichaDeTreino(aluno);
                } else {
                    System.out.println("Erro: Aluno com matrícula " + matricula + " não encontrado.");
                }
                break;
            default:
                System.out.println("Opção de relatório inválida.");
            }
        }
}