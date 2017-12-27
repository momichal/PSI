import java.util.ArrayList;
import java.util.Collections;

public class Network {
    private double ratio; //współczynnik uczenia
    private int amoutOfNeurons; //ilość neuronów w sieci
    private int maxIterations = 10000;
    private Data[] learningData; //dane uczące
    private Data[] testingData; //dane testujące
    private int amountLearningData;
    private int amountTestingData;
    private Layer layer;

    public Network(double ratio, int amoutOfNeurons){
        this.ratio = ratio;
        this.amoutOfNeurons = amoutOfNeurons;

        layer = new Layer(amoutOfNeurons, ratio);

        LoadData loadData = new LoadData();
        learningData =  loadData.load("dane.txt");
        amountLearningData = loadData.getHow_many_records();

        testingData = loadData.load("test.txt");
        amountTestingData = loadData.getHow_many_records();
    }

    public void learning(){
        normalize(learningData);
        normalize(testingData);

        int iteration=0;
        ArrayList<Double> result;

        do {
            for (int i = 0; i < amountLearningData; i++) {

                result = layer.calculateLayer(learningData[i]);
                layer.modify(result.indexOf( Collections.max( result )) );

                result.clear();
            }
            iteration++;
        }while( iteration < maxIterations );

        System.out.println("Numer iteracji: " + iteration + "\n");
    }

    public void testing(){
        ArrayList<Double> result;
        ArrayList<Integer> group = new ArrayList<>();
        int winner;

        for(int i=0; i< amountTestingData; i++) {
            result = layer.calculateLayer(testingData[i]);
            winner = result.indexOf( Collections.max( result ));

            if(!group.contains(winner)){
                group.add(result.indexOf( Collections.max( result )));
            }

            System.out.println(i + ": " + testingData[i].getName() + ", zwycięzca: " + winner );

        }

        System.out.println("\nLista zwycięskich grup: ");
        for(Integer el: group)
            System.out.println(el.toString());

    }

    public void normalize(Data[] data){

        for(int i=0; i< data.length; i++){
            double lenght = data[i].getXi(0)*data[i].getXi(0) +
                    data[i].getXi(1)*data[i].getXi(1) +
                    data[i].getXi(2)*data[i].getXi(2) +
                    data[i].getXi(3)*data[i].getXi(3);
            lenght = Math.sqrt(lenght);

            data[i].setXi( data[i].getXi(0)/lenght, 0 );
            data[i].setXi( data[i].getXi(1)/lenght, 1 );
            data[i].setXi( data[i].getXi(2)/lenght, 2 );
            data[i].setXi( data[i].getXi(3)/lenght, 3 );
        }
    }

}