import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static List<Data> readCsvFile(String filename) {
        List<Data> dataList = new ArrayList<>();
        try (FileReader fr = new FileReader(filename)) {
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                Data d = new Data();
                d.x = new double[tokens.length - 1];
                for (int i=0; i<tokens.length - 1; i++) {
                    d.x[i] = Double.parseDouble(tokens[i]);
                }
                d.className = tokens[tokens.length - 1];

                dataList.add(d);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: <learning_rate> <train_set_path> <test_set_path>");
            return;
        }

        double learningRate = Double.parseDouble(args[0]);
        List<Data> trainList = readCsvFile(args[1]);
        List<Data> testList = readCsvFile(args[2]);

        Perceptron perceptron = new Perceptron(trainList.get(0).x.length, learningRate);
        Teacher teacher = new Teacher(perceptron);
        teacher.train(trainList);
        teacher.test(testList);

        while (true) {
            System.out.println(" > classify(");
            Scanner scanner = new Scanner(System.in);
            double[] attributes = new double[perceptron.getSize()];

            for (int i = 1; i <= attributes.length; i++) {
                System.out.print("\tx" + i + "=");
                attributes[i-1] = scanner.nextDouble();
            }

            System.out.println(" )");
            System.out.println("[RESULT] classification: '" + teacher.classify(attributes) + "'");
        }
    }
}