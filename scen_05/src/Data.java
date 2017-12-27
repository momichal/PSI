public class Data {
    private double[] x;
    private int how_many_x;
    private String name;

    public Data(int how_many_x) {
        this.x = new double[how_many_x];
        this.how_many_x = how_many_x;
    }

    public void setXi(double x, int i) {
        if(i < this.how_many_x) {
            this.x[i] = x;
        }

    }

    public double getXi(int i) {
        if (i < how_many_x)
            return this.x[i];
        else
            return 0.;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

