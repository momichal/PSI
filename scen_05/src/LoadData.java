import java.io.*;

public class LoadData {
    private int how_many_records;
    private int how_many_x;
    private InputStream stream;
    private InputStreamReader reader;
    private BufferedReader file;


    public Data[] load(String filename) {
        Data[] data = null;

        try {
            this.stream = new FileInputStream(new File(filename));
            this.reader = new InputStreamReader(this.stream);
            this.file = new BufferedReader(this.reader);
            this.file.readLine();
            String line = this.file.readLine();
            String[] parts = line.split(";");
            this.how_many_records = Integer.parseInt(parts[0]);
            data = new Data[this.how_many_records];
            line = this.file.readLine();
            parts = line.split(";");
            this.how_many_x = Integer.parseInt(parts[0]);

            for(int i = 0; i < this.how_many_records; ++i) {
                line = this.file.readLine();
                parts = line.split(";");
                data[i] = new Data(this.how_many_x);

                for(int j = 0; j < this.how_many_x; ++j) {
                    data[i].setXi(Double.parseDouble(parts[j]), j);
                }

                data[i].setName(parts[this.how_many_x]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public int getHow_many_records() {
        return this.how_many_records;
    }
}

