import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entidade.FaturamentoDiario;

public class App {
    public static void main(String[] args) throws Exception {
        primeiraQuestao();
        segundaQuestao();
        terceiraQuestao();
    }

    private static void primeiraQuestao() {
        int SOMA = 0, K = 0;
        for (int INDICE = 13; K < INDICE;) {
            K = K + 1;
            SOMA = SOMA + K;
        }
        System.out.println(SOMA);
    }

    private static void segundaQuestao() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int numero = sc.nextInt();

        if (isFibonacci(numero)) {
            System.out.println("O número informado pertence à sequência de Fibonacci.");
        } else {
            System.out.println("O número informado não pertence à sequência de Fibonacci.");
        }
        sc.close();
    }

    private static void terceiraQuestao() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader("dados.json"));
        ArrayList<FaturamentoDiario> listaDeFaturamentos = gson.fromJson(reader,
                new TypeToken<List<FaturamentoDiario>>() {
                }.getType());
        System.out.println(" Menor faturamento: R$" + Math.ceil(menorFaturamento(listaDeFaturamentos)) + "\n" +
                "Maior faturamento: R$ " + Math.ceil(maiorFaturamento(listaDeFaturamentos)) + "\n " +
                "Número de dias do mês que está acima da média: " + numeroDiasDoMes(listaDeFaturamentos));
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
