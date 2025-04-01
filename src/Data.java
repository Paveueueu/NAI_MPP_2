import java.util.Arrays;

public class Data {
    double[] x;
    String className;

    @Override
    public String toString() {
        return "{ x=" + Arrays.toString(x) + ", className='" + className + '\'' + " }";
    }
}
