import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        // Criando a lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        //Inserção dos funcionários na lista
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios
                .add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 10), new BigDecimal("3017.45"), "Gerente"));
        funcionarios
                .add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletrecista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 10, 02), new BigDecimal("2799.93"), "Gerente"));

        //Remove João e imprime a lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //Configuração dos formatters para data e valores numéricos
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalSymbols.setDecimalSeparator(',');
        decimalSymbols.setGroupingSeparator('.');
        DecimalFormat formaDecimal = new DecimalFormat("#,##0.00", decimalSymbols);

        //Exibeo as informações dos funcionários formatado
        System.out.println("Nome\t\tData de Nascimento\tSalário\t\tFunção");
        System.out.println("--------------------------------------------------------");
        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = funcionario.getDataNascimento().format(dataFormatter);
            String salarioFormatado = formaDecimal.format(funcionario.getSalario());
            System.out.printf("%s\t\t%s\t\t%s\t\t%s%n", funcionario.getNome(), dataNascimentoFormatada,
                    salarioFormatado, funcionario.getFuncao());
        }

        //Atualização do salário dos funcionários com um aumento de 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }

        //Exibe as informações dos funcionários no formato desejado
        System.out.println("Nome\t\tData de Nascimento\tSalário\t\tFunção");
        System.out.println("--------------------------------------------------------");
        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = funcionario.getDataNascimento().format(dataFormatter);
            String salarioFormatado = formaDecimal.format(funcionario.getSalario());
            System.out.printf("%s\t\t%s\t\t%s\t\t%s%n", funcionario.getNome(), dataNascimentoFormatada,
                    salarioFormatado, funcionario.getFuncao());
        }

        //Agrupa os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        //Imprime os funcionários agrupados por função
        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> listaFuncionarios = entry.getValue();
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : listaFuncionarios) {
                System.out.println("Nome: " + funcionario.getNome());
            }
            System.out.println();
        }

        // Imprime os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionários que fazem aniversário em outubro (10) e dezembro (12):");
        DateTimeFormatter mesFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.getDefault());
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == Month.OCTOBER.getValue() || mesAniversario == Month.DECEMBER.getValue()) {
                String mesAniversarioFormatado = funcionario.getDataNascimento().format(mesFormatter);
                System.out.println(
                        "Nome: " + funcionario.getNome() + " - Mês de Aniversário: " + mesAniversarioFormatado);
            }
        }

        // Imprime o funcionário com a maior idade
        System.out.println("Funcionário com a maior idade:");
        Funcionario funcionarioMaiorIdade = null;
        int maiorIdade = 0;
        for (Funcionario funcionario : funcionarios) {
            int idade = funcionario.getIdade();
            if (idade > maiorIdade) {
                maiorIdade = idade;
                funcionarioMaiorIdade = funcionario;
            }
        }
        if (funcionarioMaiorIdade != null) {
            System.out.println("Nome: " + funcionarioMaiorIdade.getNome() + " - Idade: " + maiorIdade);
        }

        // Imprimie a lista de funcionários em ordem alfabética
        System.out.println("Lista de funcionários em ordem alfabética:");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
        }

        // Imprime o total dos salários dos funcionários
        System.out.println("Total dos salários dos funcionários:");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        DecimalFormat formaDecimal2 = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
        String totalSalariosFormatado = formaDecimal2.format(totalSalarios);
        System.out.println("Total: " + totalSalariosFormatado);

        // Imprime quantos salários mínimos ganha cada funcionário
        System.out.println("Quantidade de salários mínimos ganhos por cada funcionário:");
        for (Funcionario funcionario : funcionarios) {
            int salariosMinimos = funcionario.getSalariosMinimos();
            System.out.println("Nome: " + funcionario.getNome() + " - Salários Mínimos: " + salariosMinimos);
        }

    }
}