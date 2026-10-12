public abstract class Empregado implements Pagamento {

    private final String nome;
    private final String matricula;
    private final String cargo;

    public Empregado(String nome, String matricula, String cargo) {
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCargo() {
        return cargo;
    }

}
