public class Neuron {
    private double ratio;
    private double[] weight;

    public Neuron(double ratio) {
        this.ratio = ratio;
        this.weight = new double[4];

        for(int i = 0; i < 4; ++i) {
            this.weight[i] = Math.random();
        }

        this.normalizeWeight();
    }

    double activationF(Data data) {
        double activation = 0.0;

        for(int i = 0; i < 4; ++i)
            activation += data.getXi(i) * this.weight[i];

        return activation;
    }

    public void modify(Data data) {
        for(int i = 0; i < 4; ++i) {
            this.weight[i] += this.ratio * (data.getXi(i) - this.weight[i]);
        }

        this.normalizeWeight();
    }

    private void normalizeWeight() {
        double lenghtSquared = this.weight[0] * this.weight[0] + this.weight[1] * this.weight[1] + this.weight[2] * this.weight[2] + this.weight[3] * this.weight[3];
        double lenght = Math.sqrt(lenghtSquared);
        this.weight[0] /= lenght;
        this.weight[1] /= lenght;
        this.weight[2] /= lenght;
        this.weight[3] /= lenght;
    }
}
