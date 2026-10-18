import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Aqui será guardado todos os subtipos que impementam a interface <<Pagamento>>
        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        // Adicionando os objetos de cada classe que implementa a interface Pagamento
        pagamentos.add(new EmpregadoAssalariado("Ana Souza", "A001", "Analista", 1800.00f));
        pagamentos.add(new EmpregadoAssalariado("Bruno Lima", "A002", "Assistente", 1500.00f));

        pagamentos.add(new FuncionariosHoristas("Carla Mendes", "H001", "Técnica", 25.00f,
                new float[] { 8.0f, 8.0f, 8.0f, 8.0f, 10.5f }));
        pagamentos.add(new FuncionariosHoristas("Diego Alves", "H002", "Operador", 22.50f,
                new float[] { 7.5f, 8.0f, 8.0f, 7.5f, 11.0f }));

        pagamentos.add(new FuncionariosComissionados("Elisa Rocha", "C001", "Vendedora", 12000.00f, 8.0f));
        pagamentos.add(new FuncionariosComissionados("Felipe Costa", "C002", "Vendedor", 9500.00f, 7.5f));

        pagamentos.add(new Comissionado_Assalariado("Giovana Reis", "CA001", "Supervisora", 2000.00f, 10000.00f, 5.0f));
        pagamentos.add(new Comissionado_Assalariado("Heitor Nunes", "CA002", "Coordenador", 2300.00f, 14000.00f, 4.0f));

        System.out.println("=== Dados dos funcionários e remuneração ===");
        for (Pagamento pagamento : pagamentos) {
            if (pagamento instanceof Empregado empregado) {
                exibirResumoEmpregado(empregado);
            }
        }

        System.out.println("\nPressione Enter para iniciar o processamento dos pagamentos...");
        new Scanner(System.in).nextLine();

        System.out.println("\n=== Processamento da folha de pagamento ===");
        for (Pagamento pagamento : pagamentos) {
            try {
                float valor = pagamento.processarPagamento();
                if (pagamento instanceof Empregado empregado) {
                    System.out.print("\n");
                    System.out.println("Nome do funcionário: " + empregado.getNome());
                }
                System.out.printf("Valor do pagamento: R$ %.2f%n", valor);
            } catch (ExcessoHorasExtrasException e) {
                if (pagamento instanceof FuncionariosHoristas horista) {
                    float valor = horista.calcularValorPagamento();
                    System.out.print("\n");
                    System.out.printf(
                            "PAGAMENTO SINALIZADO - Excesso de horas extras. Funcionário: %s | Valor: R$ %.2f%n",
                            horista.getNome(), valor
                    );
                }
            }
        }
    }

    private static void exibirResumoEmpregado(Empregado empregado) {
        System.out.printf("%nNome: %s | Matrícula: %s | Cargo: %s%n",
                empregado.getNome(), empregado.getMatricula(), empregado.getCargo());

        if (empregado instanceof EmpregadoAssalariado assalariado) {
            System.out.printf("Tipo: Assalariado | Salário fixo semanal: R$ %.2f | Remuneração prevista: R$ %.2f%n",
                    assalariado.getSalarioSemanal(), assalariado.calcularValorPagamento());
        } else if (empregado instanceof FuncionariosHoristas horista) {
            float[] horasPorDia = horista.getHorasPorDia();
            System.out.printf(
                    "Tipo: Horista | Valor por hora: R$ %.2f | Horas por dia: %s | Total semanal: %.2f horas | Remuneração prevista: R$ %.2f%n",
                    horista.getValorHora(), Arrays.toString(horasPorDia),
                    horista.getTotalHorasTrabalhadas(), horista.calcularValorPagamento());
            if (horista.temExcessoHorasExtrasDiarias()) {
                System.out.println("Atenção: ultrapassa duas horas extras em pelo menos um dia.");
            }
        } else if (empregado instanceof FuncionariosComissionados comissionado) {
            System.out.printf(
                    "Tipo: Comissionado | Vendas semanais: R$ %.2f | Comissão: %.2f%% | Remuneração prevista: R$ %.2f%n",
                    comissionado.getVendasSemanais(), comissionado.getPercentualComissao(),
                    comissionado.calcularValorPagamento());
        } else if (empregado instanceof Comissionado_Assalariado comissionadoAssalariado) {
            System.out.printf(
                    "Tipo: Assalariado/comissionado | Salário-base semanal: R$ %.2f | Salário-base com adicional de 10%%: R$ %.2f | Vendas semanais: R$ %.2f | Comissão: %.2f%% | Remuneração prevista: R$ %.2f%n",
                    comissionadoAssalariado.getSalarioBaseSemanal(),
                    comissionadoAssalariado.getSalarioBaseComReajuste(),
                    comissionadoAssalariado.getVendasSemanais(),
                    comissionadoAssalariado.getPercentualComissao(),
                    comissionadoAssalariado.calcularValorPagamento());
        }
    }
}
