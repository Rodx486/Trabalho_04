public class FuncionariosHoristas extends Empregado {

    private static final float LIMITE_HORAS_NORMAIS = 40.0f;
    private static final float MULTIPLICADOR_HORA_EXTRA = 1.5f;
    private static final float LIMITE_HORAS_NORMAIS_DIARIAS = 8.0f;
    private static final float LIMITE_HORAS_EXTRAS_DIARIAS = 2.0f;
    private static final float LIMITE_JORNADA_DIARIA = LIMITE_HORAS_NORMAIS_DIARIAS + LIMITE_HORAS_EXTRAS_DIARIAS;

    private final float valorHora;
    private final float[] horasPorDia;

    public FuncionariosHoristas(String nome, String matricula, String cargo,
            float valorHora, float[] horasPorDia) {
        super(nome, matricula, cargo);

        if (valorHora < 0) {
            throw new DadosInvalidosException("O valor da hora não pode ser negativo.");
        }
        if (horasPorDia == null) {
            throw new DadosInvalidosException("O registro de horas não pode ser nulo.");
        }

        for (int i = 0; i < horasPorDia.length; i++) {
            if (horasPorDia[i] < 0) {
                throw new DadosInvalidosException(
                "As horas do dia " + (i + 1) + " não podem ser negativas.");
    }
        }
        this.valorHora = valorHora;
        this.horasPorDia = horasPorDia.clone();
    }

    public float getValorHora() {
        return valorHora;
    }

    public float[] getHorasPorDia() {
        return horasPorDia.clone();
    }

    public float getTotalHorasTrabalhadas() {
        float totalHorasTrabalhadas = 0.0f;
        for (float horas : horasPorDia) {
            totalHorasTrabalhadas += horas;
        }
        return totalHorasTrabalhadas;
    }

    public boolean temExcessoHorasExtrasDiarias() {
        for (float horas : horasPorDia) {
            if (horas > LIMITE_JORNADA_DIARIA) {
                return true;
            }
        }
        return false;
    }

    public float calcularValorPagamento() {

        float totalHorasTrabalhadas = getTotalHorasTrabalhadas();

        float pagamento = 0.0f;

        if (totalHorasTrabalhadas <= LIMITE_HORAS_NORMAIS) {
            pagamento = totalHorasTrabalhadas * valorHora;
        } else {
            float horasExtras = totalHorasTrabalhadas - LIMITE_HORAS_NORMAIS;
            pagamento = (LIMITE_HORAS_NORMAIS * valorHora) + (horasExtras * valorHora * MULTIPLICADOR_HORA_EXTRA);
        }
        return pagamento;
    }

    private void validarHorasExtrasDiarias() throws ExcessoJornadaException {
        for (int i = 0; i < horasPorDia.length; i++) {
            if (horasPorDia[i] > LIMITE_JORNADA_DIARIA) {
                throw new ExcessoJornadaException(
                        getNome(), i + 1, horasPorDia[i], LIMITE_JORNADA_DIARIA);
            }
        }
    }

    @Override
    public float processarPagamento() throws ExcessoJornadaException {
        validarHorasExtrasDiarias();

        float pagamento = calcularValorPagamento();

        return pagamento;
    }
}
