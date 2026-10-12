public class EmpregadoAssalariado extends Empregado {

    private final float salarioSemanal;

    public EmpregadoAssalariado(String nome, String matricula, String cargo, float salarioSemanal) {
        super(nome, matricula, cargo);
        if (salarioSemanal < 0) {
            throw new IllegalArgumentException("O salário semanal não pode ser negativo.");
        }
        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public float processarPagamento() {
        return salarioSemanal;
    }
}
