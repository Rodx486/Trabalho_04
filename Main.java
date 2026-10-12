import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Aqui será guardado todos os subtipos que impementam a interface Pagamento
        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        // Adicionando os objetos de cada classe que implementa a interface Pagamento
        pagamentos.add(new EmpregadoAssalariado("Ana Souza", "A001", "Analista", 1800.00f));
        pagamentos.add(new EmpregadoAssalariado("Bruno Lima", "A002", "Assistente", 1500.00f));

        pagamentos.add(new FuncionariosHoristas("Carla Mendes", "H001", "Técnica", 25.00f,
                new float[] {8.0f, 8.0f, 8.0f, 8.0f, 10.0f}));
        pagamentos.add(new FuncionariosHoristas("Diego Alves", "H002", "Operador", 22.50f,
                new float[] {7.5f, 8.0f, 8.0f, 7.5f, 7.0f}));

        pagamentos.add(new FuncionariosComissionados("Elisa Rocha", "C001", "Vendedora", 12000.00f, 8.0f));
        pagamentos.add(new FuncionariosComissionados("Felipe Costa", "C002", "Vendedor", 9500.00f, 7.5f));

        pagamentos.add(new Comissionado_Assalariado("Giovana Reis", "CA001", "Supervisora", 2000.00f, 10000.00f, 5.0f));
        pagamentos.add(new Comissionado_Assalariado("Heitor Nunes", "CA002", "Coordenador", 2300.00f, 14000.00f, 4.0f));

        //processa os pagamentos de cada objeto que implementa a interface Pagamento
        pagamentos.forEach(pagamento -> {
            float valorPagamento = pagamento.processarPagamento();
            System.out.printf("Valor do pagamento: R$ %.2f%n", valorPagamento);
        });
   
   
    }

    
}
