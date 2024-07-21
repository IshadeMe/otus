package solver;

public class App {

    public static final double DELTA = 0.0001;

    /**
     * a, b, c - коэффициенты квадратного уравнения
     *
     * @return список корней квадратного уравнения
     * <br> ax^2 + bx + c = 0
     */
    public double[] solve(double a, double b, double c) {
        if (compareWithZero(a, 0) == 0 || isInvalidInputs(a, b, c)) {
            throw new IllegalArgumentException();
        }

        var result = new double[0];
        var discriminant = b * b - 4 * a * c;
        var comparedDiscriminant = compareWithZero(discriminant, 0);

        if (comparedDiscriminant > 0) {
            result = new double[2];
            result[0] = ((-1 * b) + Math.sqrt(discriminant)) / (2 * a);
            result[1] = ((-1 * b) - Math.sqrt(discriminant)) / (2 * a);
        }
        if (comparedDiscriminant == 0) {
            result = new double[1];
            result[0] = (-1 * b) / (2 * a);
        }

        return result;
    }

    private boolean isInvalidInputs(double... args) {
        for (double arg : args) {
            if (Double.isInfinite(arg) || Double.isNaN(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Сравнивает два double числа с учетом некоторой погрешности DELTA
     * @param a число a
     * @param b число b
     * @return 0 если числа можно считать равными, <br>1 если a > b,<br> -1 если a < b
     */
    private int compareWithZero(double a, double b) {
        var compared = a > 0 ? 1 : -1;
        return Math.abs(a - b) <= DELTA ? 0 : compared;
    }

}
