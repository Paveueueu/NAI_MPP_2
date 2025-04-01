import java.util.*;

public class Teacher {
    private final Perceptron perceptron;
    private String class0;
    private String class1;

    int correct_answers = 0;
    int total_answers = 0;

    Teacher(Perceptron perceptron) {
        this.perceptron = perceptron;
    }

    void train(List<Data> trainList) {

        Set<String> temp = new HashSet<>();
        for (Data data : trainList) {
            temp.add(data.className);
        }
        List<String> uniqueClassNames = new ArrayList<>(temp);

        class0 = uniqueClassNames.get(0);
        class1 = uniqueClassNames.get(1);

        Collections.shuffle(trainList);
        for (Data d : trainList) {
            System.out.println("Training " + d);
            perceptron.train(d.x, d.className.equals(class1));
        }
    }

    void test(List<Data> testList) {
        for (Data d : testList) {
            boolean classIndicator = d.className.equals(class1);
            boolean isCorrect = perceptron.test(d.x, classIndicator);
            System.out.println("[TEST] " + (isCorrect ? "CORRECT" : "INCORRECT") +
                    "   classification: '" + (classIndicator ? class1 : class0) + "'   label: '" + d.className + "'");

            total_answers++;
            if (isCorrect) correct_answers++;
        }

        double accuracy = testList.isEmpty() ? 1.0 : (double) correct_answers / testList.size() * 100;
        System.out.println("-------------------------");
        System.out.println("Accuracy: " + accuracy + "%");
        System.out.println("-------------------------\n");
    }

    String classify(double[] input) {
        boolean classification = perceptron.classify(input);
        return classification ? class1 : class0;
    }
}
