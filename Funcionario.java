import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public int getIdade() {
        LocalDate dataAtual = LocalDate.now();
        return Period.between(getDataNascimento(), dataAtual).getYears();
    }

    public int getSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        BigDecimal quantidadeSalariosMinimos = getSalario().divide(salarioMinimo, BigDecimal.ROUND_DOWN);
        return quantidadeSalariosMinimos.intValue();
    }
}