import java.util.Random;

public class Neuron {

    private double[] weights;
    private boolean ifRandom = false;

    public Neuron () {
        randomWeight();
    }

    public void randomWeight()
    {
        Random rand = new Random();
        weights = new double[3];

        for(int i=0; i<3; i++) {
            weights[i] = rand.nextDouble();
        }

        ifRandom = true;
    }

    public int getMembranePotential(int[] tab) {
        double membranePotential = 0;
        for ( int i = 0; i < 3; i++ )
            membranePotential += tab[i] * weights[i];

        return activationBlock(membranePotential);
    }

    private int activationBlock(double membranePotential) {
        if(membranePotential < 0)
            return 0;
        else
            return 1;
    }

    public void learning( int[] tab, double y, double ratio ) {
        double membranePotential = getMembranePotential(tab);

        for ( int i = 0; i < 3; i++ ) {
            weights[i] += tab[i] * (y - membranePotential) * ratio;
        }

    }

    public void processLearning(Neuron neuron, double amount, double ratio, int[] p, int[] q, int[] y, int x0)
    {
        /* Proces uczenia */
        for ( int j = 0; j < amount; j++ ) {
            for (int i = 0; i < 4; i++) {
                neuron.learning(new int[]{x0, p[i], q[i]}, y[i], ratio);
            }
            //}

        /* Przy której iteracji uzyskaliśmy poprawny rezultat */

            int[] test = new int[4];
            for (int i = 0; i < 4; i++) {
                if (y[i] == neuron.getMembranePotential(new int[]{x0, p[i], q[i]})) {
                    test[i] = 1;
                } else {
                    test[i] = 0;
                }
            }

            if (test[0] == 1 && test[1] == 1 && test[2] == 1 && test[3] == 1) {
                System.out.println("\nUdało się :) przy iteracji " + (j+1));
                neuron.resultView(neuron,j+1,p,q,y,x0);
                break;

            } else {
                System.out.print("Niestety :(  przy iteracji " + (j+1));
            }
            System.out.println();
        }

    }


    public void resultView(Neuron neuron, double amount, int[] p, int[]q, int[] y, int x0) {

        System.out.println("Ilość powtórzeń = " + amount);
        System.out.println(p[0] + " AND " + q[0] + " | correctVal: " + y[0] +
                " | result: " + neuron.getMembranePotential(new int[] {x0, p[0], q[0]}));
        System.out.println(p[1] + " AND " + q[1] + " | correctVal: " + y[1] +
                " | result: " + neuron.getMembranePotential(new int[] {x0, p[1], q[1]}));
        System.out.println(p[2] + " AND " + q[2] + " | correctVal: " + y[2] +
                " | result: " + neuron.getMembranePotential(new int[] {x0, p[2], q[2]}));
        System.out.println(p[3] + " AND " + q[3] + " | correctVal: " + y[3] +
                " | result: " + neuron.getMembranePotential(new int[] {x0, p[3], q[3]}));


    }

    public boolean getIfRandom()
    {
        return this.ifRandom;
    }
}
