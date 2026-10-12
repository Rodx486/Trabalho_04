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
            throw new IllegalArgumentException("Valores não podem ser negativos e a comissão deve estar entre 0 e 100%.");
        }
        this.salarioBaseSemanal = salarioBaseSemanal;
        this.vendasSemanais = vendasSemanais;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public float processarPagamento() {
        float salarioBaseComReajuste = salarioBaseSemanal * (1 + REAJUSTE_TEMPORARIO);
        return salarioBaseComReajuste + (vendasSemanais * percentualComissao / 100.0f);
    }
}
