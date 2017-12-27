import java.util.ArrayList;

public class Layer {
    public Neuron[] neurons;
    private int amoutOfNeurons;
    private double ratio;
    private Data data;

    public Layer(int amoutOfNeurons, double ratio) {
        this.amoutOfNeurons = amoutOfNeurons;
        this.ratio = ratio;
        this.neurons = new Neuron[this.amoutOfNeurons];

        for(int i = 0; i < this.amoutOfNeurons; ++i) {
            this.neurons[i] = new Neuron(this.ratio);
        }

    }

    public ArrayList<Double> calculateLayer(Data input) {
        ArrayList<Double> results = new ArrayList<>();
        this.data = input;

        for(int i = 0; i < this.neurons.length; ++i) {
            results.add(this.neurons[i].activationF(input));
        }

        return results;
    }

    public void modify(int id) {
        this.neurons[id].modify(this.data);
    }
}
