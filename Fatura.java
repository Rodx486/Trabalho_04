public class Fatura implements Pagamento {

    // enunciado não fala o que "FATURA" deve conter, vou colocar um código simples
    // qualquer.
    private final int quantidade;
    private final float precoPorItem;

    public Fatura(int quantidade, float precoPorItem) {
        if (quantidade <= 0) {
            throw new DadosInvalidosException(
                    "A quantidade de itens deve ser maior que zero.");
        }
        if (precoPorItem <= 0) {
            throw new DadosInvalidosException(
                    "O preço por item não pode ser negativo.");
        }
        this.quantidade = quantidade;
        this.precoPorItem = precoPorItem;
    }

    @Override
    public float processarPagamento() {
        return quantidade * precoPorItem;
    }
}
