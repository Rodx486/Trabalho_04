public class Comissionado_Assalariado extends Empregado {

    private static final float REAJUSTE_TEMPORARIO = 0.10f;

    private final float salarioBaseSemanal;
    private final float vendasSemanais;
    private final float percentualComissao;

    public Comissionado_Assalariado(String nome, String matricula, String cargo,
            float salarioBaseSemanal, float vendasSemanais, float percentualComissao) {
        super(nome, matricula, cargo);
        if (salarioBaseSemanal < 0 || vendasSemanais < 0
                || percentualComissao < 0 || percentualComissao > 100) {
            throw new DadosInvalidosException("Valores não podem ser negativos e a comissão deve estar entre 0 e 100%.");
        }
        this.salarioBaseSemanal = salarioBaseSemanal;
        this.vendasSemanais = vendasSemanais;
        this.percentualComissao = percentualComissao;
    }

    public float getSalarioBaseSemanal() {
        return salarioBaseSemanal;
    }

    public float getVendasSemanais() {
        return vendasSemanais;
    }

    public float getPercentualComissao() {
        return percentualComissao;
    }

    public float getSalarioBaseComReajuste() {
        return salarioBaseSemanal * (1 + REAJUSTE_TEMPORARIO);
    }

    public float calcularValorPagamento() {
        return getSalarioBaseComReajuste() + (vendasSemanais * percentualComissao / 100.0f);
    }

    @Override
    public float processarPagamento() {
        return calcularValorPagamento();
    }
}
