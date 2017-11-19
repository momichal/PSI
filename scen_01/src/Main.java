public class Main {

    public static void main ( String[] args ) {

        double ratio = 0.9; //współczynnik uczenia
        double maxAmount = 100; //ilość powtórzeń
        boolean ifRandom = false;
        Neuron neuron = new Neuron();

        int x0 = 1; //bias

        /* Wygenerowanie danych uczących */
        int[] p = { 0, 0, 1, 1 };
        int[] q = { 0, 1, 0, 1 };

        /* Wygenerowanie danych testujących */
        int[] y = { 0, 0, 0, 1 };


        System.out.println("Współczynnik uczenia = " + ratio);
        System.out.println("Maksymalna ilość iteracji = " + maxAmount);
        System.out.println("Losowe wagi: " + neuron.getIfRandom());
        System.out.println();

        neuron.processLearning(neuron, maxAmount, ratio, p, q, y, x0);

    }
}
