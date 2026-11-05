import java.util.Objects;

public class PayrollService {

    public void processarLote(Pagamento[] pagamentos)
            throws FalhaProcessamentoFolhaException {
        Objects.requireNonNull(pagamentos, "A lista de pagamentos não pode ser nula.");

        HistoricoTransacoes historico = new HistoricoTransacoes();

        try {
            for (Pagamento pagamento : pagamentos) {
                try {
                    float valor = pagamento.processarPagamento();
                    historico.registrarPagamento(pagamento);
                    System.out.printf("Pagamento processado: R$ %.2f%n", valor);
                } catch (ExcessoJornadaException e) {
                    System.out.printf(
                            "AUDITORIA TRABALHISTA: %s, dia %d, %.2f horas registradas (limite: %.2f).%n",
                            e.getNomeColaborador(),
                            e.getDiaRegistro(),
                            e.getHorasLancadas(),
                            e.getLimiteLegalPermitido());
                    throw new FalhaProcessamentoFolhaException(
                            "Falha ao processar a folha devido a excesso de jornada.", e);
                }
            }
        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("Falha no lote. Iniciando rollback.");
            historico.estornarTodos();
            throw e;
        } catch (RuntimeException e) {
            System.out.println("Falha inesperada no lote. Iniciando rollback.");
            historico.estornarTodos();
            throw e;
        } finally {
            historico.imprimirResumoAuditoria();
        }
    }
}
