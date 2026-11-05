import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class HistoricoTransacoes {

    private final Deque<Pagamento> pagamentosProcessados = new ArrayDeque<>();
    private int totalProcessados;
    private int totalEstornados;

    public void registrarPagamento(Pagamento pagamento) {
        Objects.requireNonNull(pagamento, "O pagamento não pode ser nulo.");
        pagamentosProcessados.push(pagamento);
        totalProcessados++;
    }

    public int estornarTodos() {
        int estornadosNestaOperacao = 0;

        while (!pagamentosProcessados.isEmpty()) {
            Pagamento pagamento = pagamentosProcessados.pop();
            System.out.printf("Estorno (LIFO): %s%n", identificar(pagamento));
            totalEstornados++;
            estornadosNestaOperacao++;
        }

        return estornadosNestaOperacao;
    }

    public void imprimirResumoAuditoria() {
        System.out.printf(
                "Resumo da auditoria: %d pagamento(s) processado(s), %d estorno(s).%n",
                totalProcessados, totalEstornados);
    }

    private String identificar(Pagamento pagamento) {
        if (pagamento instanceof Empregado empregado) {
            return empregado.getNome();
        }
        return pagamento.getClass().getSimpleName();
    }
}
