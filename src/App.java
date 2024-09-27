import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        primeiraQuestao();
        segundaQuestao();
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
