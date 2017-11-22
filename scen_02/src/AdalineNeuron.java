public class AdalineNeuron {

    private int[][] lettersMy;
    private int[] bigOrSmall;
    private double[] weight;
    double[] membranePotential;

    private double learning_ratio = 0.00001;
    private double maxIteration = 2000;
    private int amountOfLetters = 20;

    public AdalineNeuron(int[][] letters, int[] bigOrSmall){
        this.lettersMy = letters;
        this.bigOrSmall = bigOrSmall;

        this.weight = new double[35];
        membranePotential = new double[amountOfLetters]; // Zawiera potencjał membranowy dla każdej litery
    }

    public void learn(AdalineNeuron adaline)
    {
        int[] expectedResults = bigOrSmall; //wyniki oczekiwane [ 1 - duża, 0 - mała litera ]
        double error = 0;

        for(int i=0; i<35; i++){
            weight[i] = Math.random();
        }

        int iteration = 0;
        for(int i=0; i<maxIteration; i++)
        {
            iteration++;

            // Wykonuj dla każdej litery uczącej
            for (int j=0; j<amountOfLetters; j++)
            {
                int[] oneLetterTab = lettersMy[j]; // Prześlij adres początku tablicy litery
                membranePotential[j] = adaline.getMembranePotential(oneLetterTab, weight);

                // Modyfikacja wag wg wzoru
                for ( int k = 0; k < weight.length; k++ ) {
                    weight[k] += oneLetterTab[k] * (expectedResults[j] - membranePotential[j]) * learning_ratio;
                }
                error = 0.5*(expectedResults[j] - membranePotential[j])*(expectedResults[j] - membranePotential[j]);
            }

            // Jeśli błąd mniejszy od założonego progu - zakończ!
            if(error<0.0001) {
                break;
            }
        }
        resultWithActivation(adaline, iteration);
    }


    // Wynik z użyciem funkcji aktywacji
    public void resultWithActivation(AdalineNeuron adaline, int iteration) {
        System.out.println("Ilość wykonanych iteracji: " + iteration);
        for(int i=0; i<20;i++){
            System.out.print("Litera nr " + i + ": ");
            if(adaline.activationBlock(membranePotential[i])==1) {
                System.out.println("DUŻA");
            }else{
                System.out.println("mała");
            }
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
    private int activationBlock(double membranePotential) {
        if(membranePotential <= 0.5)
            return 0;
        else
            return 1;
    }

    public void test(int[][] test_letters, AdalineNeuron adaline) {

        double[] sum = new double[amountOfLetters];
        int[] tab;
        System.out.println("Test:");
        for (int i = 0; i<4; i++){
            tab = test_letters[i];
            sum[i] = adaline.getMembranePotential(tab, weight);
            System.out.print("Litera nr " + i + ": ");
            if(adaline.activationBlock(sum[i])==1) {
                System.out.println("big");
            }else{
                System.out.println("small");
            }
        }
    }

    // Wyświetla tablicę liter
    public void printLetters(int[][] letters){
        for(int i =0; i<7; i++){
            for(int j = 0; j< letters.length; j++){
                for(int k=0; k<5; k++){
                    System.out.print(letters[j][(i*5) + k]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}