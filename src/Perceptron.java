public class Perceptron {

    private final int size;
    private final double[] weights;
    private final double alpha;
    private double theta;

    public int getSize() {
        return size;
    }

    Perceptron(int size) {
        this.size = size;
        this.weights = new double[size];
        this.theta = 0;
        this.alpha = 0.01;
    }

    boolean classify(double[] input) {
        double output = 0.0;
        for (int i = 0; i<size; i++) {
            output += weights[i] * input[i];
        }

        return output - theta > 0;
    }

    void train(double[] input, boolean correctOutput) {
        boolean current = classify(input);
        int error = (correctOutput? 1 : 0) - (current? 1 : 0);

        for (int i=0; i<size; i++) {
            weights[i] += error * alpha * input[i];
        }
        theta -= alpha * error;
    }

    boolean test(double[] input, boolean correctOutput) {
        return classify(input) == correctOutput;
    }
}
