public class Main {

    public static void main (String[]args){
        double ratio = 0.7;
        int amoutOfNeurons = 30;

        Network network = new Network(ratio, amoutOfNeurons);
            network.learning();
            network.testing();
        }
}
