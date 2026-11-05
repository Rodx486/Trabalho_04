public class ExcessoJornadaException extends Exception {

    private final String nomeColaborador;
    private final int diaRegistro;
    private final float horasLancadas;
    private final float limiteLegalPermitido;

    public ExcessoJornadaException(String nomeColaborador, int diaRegistro,
            float horasLancadas, float limiteLegalPermitido) {
        super(String.format(
                "Excesso de jornada para %s no dia %d: %.2f horas lançadas; limite legal de %.2f horas.",
                nomeColaborador, diaRegistro, horasLancadas, limiteLegalPermitido));
        this.nomeColaborador = nomeColaborador;
        this.diaRegistro = diaRegistro;
        this.horasLancadas = horasLancadas;
        this.limiteLegalPermitido = limiteLegalPermitido;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public int getDiaRegistro() {
        return diaRegistro;
    }

    public float getHorasLancadas() {
        return horasLancadas;
    }

    public float getLimiteLegalPermitido() {
        return limiteLegalPermitido;
    }
}
