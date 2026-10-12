public class FuncionariosHoristas extends Empregado {

    private static final float LIMITE_HORAS_NORMAIS = 40.0f;
    private static final float MULTIPLICADOR_HORA_EXTRA = 1.5f;
    private static final float LIMITE_HORAS_NORMAIS_DIARIAS = 8.0F;
    
    private final float valorHora;
    private final float[] horasPorDia;

    public FuncionariosHoristas(String nome, String matricula, String cargo,
            float valorHora, float[] horasPorDia) {
        super(nome, matricula, cargo);
        this.valorHora = valorHora;
        this.horasPorDia = horasPorDia.clone();
    }

    public void validarHorasExtras() throws ExcessoHorasExtrasException {
        for (float horas : horasPorDia) {
            if (horas > LIMITE_HORAS_NORMAIS_DIARIAS) {
                throw new ExcessoHorasExtrasException("Excesso de horas extras em um dia.");
            }
        }
    }

    @Override
    public float processarPagamento() {

        float totalHorasTrabalhadas = 0.0f;
        for (float horas : horasPorDia) {
            
            totalHorasTrabalhadas += horas;
        }

        float pagamento = 0.0f;

        if (totalHorasTrabalhadas <= LIMITE_HORAS_NORMAIS) {
            pagamento = totalHorasTrabalhadas * valorHora;
        } else {
            float horasExtras = totalHorasTrabalhadas - LIMITE_HORAS_NORMAIS;
            pagamento = (LIMITE_HORAS_NORMAIS * valorHora) + (horasExtras * valorHora * MULTIPLICADOR_HORA_EXTRA);
        }
        return pagamento;
    }
}
