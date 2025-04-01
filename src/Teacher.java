import java.util.*;

public class Teacher {
    private final Perceptron perceptron;
    private String class0;
    private String class1;

    private int correct_class0 = 0;
    private int correct_class1 = 0;
    private int total_class0 = 0;
    private int total_class1 = 0;

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

            if (!classIndicator) {
                if (isCorrect) {
                    correct_class0++;
                }
                total_class0++;
            }
            else {
                if (isCorrect) {
                    correct_class1++;
                }
                total_class1++;
            }
        }

        double accuracy = testList.isEmpty() ? 1.0 : (double) (correct_class0 + correct_class1) / testList.size() * 100;
        double accuracy_class0 = testList.isEmpty() ? 1.0 : (double) correct_class0 / total_class0 * 100;
        double accuracy_class1 = testList.isEmpty() ? 1.0 : (double) correct_class1 / total_class1 * 100;
        System.out.println("-------------------------");
        System.out.println("Accuracy: " + accuracy + " %");
        System.out.println(class0 +  " accuracy: " + accuracy_class0 + " %");
        System.out.println(class1 + " accuracy: " + accuracy_class1 + " %");
        System.out.println("-------------------------\n");
    }

    String classify(double[] input) {
        boolean classification = perceptron.classify(input);
        return classification ? class1 : class0;
    }
}
