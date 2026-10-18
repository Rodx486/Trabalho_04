public class Fatura implements Pagamento {

  // enunciado não fala o que "FATURA" deve conter, vou colocar um código simples qualquer.
    private final int quantidade;
    private final float precoPorItem;

    public Fatura(int quantidade, float precoPorItem) {
        this.quantidade = quantidade;
        this.precoPorItem = precoPorItem;
    }

    @Override
    public float processarPagamento() {
        return quantidade * precoPorItem;
    }
}
