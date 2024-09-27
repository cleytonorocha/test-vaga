import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entidade.FaturamentoDiario;
import enums.FaturamentoMensalDistribuidora;

public class App {
    public static void main(String[] args) throws Exception {
        primeiraQuestao();
        segundaQuestao();
        terceiraQuestao();
        quartaQuestao();
        quintaQuestao();
    }

    private static void primeiraQuestao() {
        System.out.println("1°) ");

        int SOMA = 0, K = 0;
        for (int INDICE = 13; K < INDICE;) {
            K = K + 1;
            SOMA = SOMA + K;
        }
        System.out.println("Resultado da soma: " + SOMA);
    }

    private static void segundaQuestao() {
        System.out.println("2°) ");

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int numero = sc.nextInt();

        if (isFibonacci(numero)) {
            System.out.println("O número informado pertence à sequência de Fibonacci.");
        } else {
            System.out.println("O número informado não pertence à sequência de Fibonacci.");
        }
    }

    private static void terceiraQuestao() throws FileNotFoundException {
        System.out.println("3°) ");

        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader("dados.json"));
        ArrayList<FaturamentoDiario> listaDeFaturamentos = gson.fromJson(reader,
                new TypeToken<List<FaturamentoDiario>>() {
                }.getType());
        System.out.println("Menor faturamento: R$" + Math.ceil(menorFaturamento(listaDeFaturamentos)) + "\n" +
                "Maior faturamento: R$ " + Math.ceil(maiorFaturamento(listaDeFaturamentos)) + "\n" +
                "Número de dias do mês que está acima da média: " + numeroDiasDoMes(listaDeFaturamentos));
    }

    private static void quartaQuestao() {
        System.out.println("4°) ");

        Double total = Stream.of(FaturamentoMensalDistribuidora.values())
                .map(FaturamentoMensalDistribuidora::getFaturamento)
                .reduce((valor1, valor2) -> valor1 + valor2)
                .orElseThrow(() -> new RuntimeException("Não foi possível executar a soma dos valores"));

                System.out.printf("Percentual de SP: %.2f%%\n", calcularPercentual(FaturamentoMensalDistribuidora.SAO_PAULO.getFaturamento(), total));
                System.out.printf("Percentual de RJ: %.2f%%\n", calcularPercentual(FaturamentoMensalDistribuidora.RIO_DE_JANEIRO.getFaturamento(), total));
                System.out.printf("Percentual de MG: %.2f%%\n", calcularPercentual(FaturamentoMensalDistribuidora.MINAS_GERAIS.getFaturamento(), total));
                System.out.printf("Percentual de ES: %.2f%%\n", calcularPercentual(FaturamentoMensalDistribuidora.ESPIRITO_SANTO.getFaturamento(), total));
                System.out.printf("Percentual de Outros: %.2f%%\n", calcularPercentual(FaturamentoMensalDistribuidora.OUTROS.getFaturamento(), total));
        
    }

    private static void quintaQuestao(){
        System.out.println("5°) ");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma string:");
        String input = scanner.nextLine();
        scanner.close();
        char[] caracteres = input.toCharArray();
        String resultado = "";
        for (int i = caracteres.length - 1; i >= 0; i--) {
            resultado += caracteres[i];
        }
        System.out.println(resultado);
    }


    public static Double calcularPercentual(Double valor, Double total) {
        return (valor / total) * 100;
    }

    private static Double menorFaturamento(ArrayList<FaturamentoDiario> lista) {
        return lista.stream()
                .map(FaturamentoDiario::getValor)
                .filter(f -> f != 0)
                .min((o1, o2) -> o1.compareTo(o2))
                .orElseThrow(() -> new RuntimeException("Lista inválida!"));
    }

    private static Double maiorFaturamento(ArrayList<FaturamentoDiario> lista) {
        return lista.stream()
                .map(FaturamentoDiario::getValor)
                .max((o1, o2) -> o1.compareTo(o2))
                .orElseThrow(() -> new RuntimeException("Lista inválida!"));
    }

    private static int numeroDiasDoMes(ArrayList<FaturamentoDiario> lista) {
        Double media = lista.stream()
                .map(FaturamentoDiario::getValor)
                .filter(f -> f != 0)
                .reduce((valor1, valor2) -> valor1 + valor2)
                .orElseThrow(() -> new RuntimeException("Código não foi capaz de somar a lista")) / lista.size();

        return lista.stream()
                .map(FaturamentoDiario::getValor)
                .filter(f -> f < media & f != 0)
                .toList()
                .size();
    }

    private static boolean isFibonacci(int numero) {
        int a = 0, b = 1, temp;
        while (a < numero) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return a == numero;
    }

}
