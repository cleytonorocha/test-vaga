public class App {
    public static void main(String[] args) throws Exception {
        primeiraQuestao();
    }

    private static void primeiraQuestao() {
        int SOMA = 0, K = 0;
        for (int INDICE = 13; K < INDICE;) {
            K = K + 1;
            SOMA = SOMA + K;
        }
        System.out.println(SOMA);
    }
}
