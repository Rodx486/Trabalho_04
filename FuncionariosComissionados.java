public class FuncionariosComissionados extends Empregado {
    private final float vendasSemanais;
    private final float percentualComissao;

    public FuncionariosComissionados(String nome, String matricula, String cargo,
            float vendasSemanais, float percentualComissao) {
        super(nome, matricula, cargo);
        if (vendasSemanais < 0 || percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException("Vendas devem ser positivas e a comissão deve estar entre 0 e 100%.");
        }
        this.vendasSemanais = vendasSemanais;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public float processarPagamento() {
        return vendasSemanais * percentualComissao / 100.0f;
    }
}

