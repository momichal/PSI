import java.util.Random;

public class Hebba {

    private int[][] lettersMy;
    public double weight[];

    double learning_ratio = 0.1;
    double forgeting_ratio = 0.4;
    private int amountOfLetters = 20;


    public Hebba(int[][] letters) {
        this.lettersMy = letters;
        this.weight = new double[35];

        Random random = new Random();
        for (int i = 0; i < weight.length; i++) {
            weight[i] = random.nextDouble();
        }

        System.out.println("Współczynnik uczenia: " + learning_ratio);
        System.out.println("Współczynnik zapominania: " + forgeting_ratio);
    }


    public void learn() {
        // Wykonuj dla każdej litery uczącej
        for (int i = 0; i < amountOfLetters; i++) {
            double membranePotential;
            int[] oneLetterTab = lettersMy[i]; // Prześlij adres początku tablicy litery

            membranePotential = getMembranePotential(oneLetterTab, weight);

            // Modyfikacja wag wg wzoru
            for (int j = 0; j < weight.length; j++) {
                weight[j] = weight[j] * forgeting_ratio + learning_ratio * activationBlock(membranePotential) * (oneLetterTab[j] - weight[j]);
            }
        }
    }

    public void test(){
        // Wykonuj dla każdej litery uczącej
        for (int i = 0; i <amountOfLetters ; i++) {
            double result;
            int[] oneLetterTab = lettersMy[i]; // Prześlij adres początku tablicy litery

            double membranePotential = getMembranePotential(oneLetterTab, weight);
            result = activationBlock(membranePotential);

            System.out.print("Litera nr " + i + ", wynik: "+ result + "\n");
        }
    }

    // Zwraca potencjał membranowy, sumuje każdy znak*waga
    public double getMembranePotential(int[] tab, double[] weight){
        double membranePotential = 0;
        for(int i=0; i < tab.length; i++)
            membranePotential += tab[i] * weight[i];
        return membranePotential;
    }

    // Funkcja aktywacji
    public double activationBlock(double membranePotential){
        double result = (1 - Math.exp(-membranePotential)) / (1 + Math.exp(-membranePotential));
        return result;
    }
}