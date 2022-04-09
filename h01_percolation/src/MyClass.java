import java.math.BigDecimal;

public class MyClass {

    public static void main(String[] args) {
        am_i_wilson(563);
    }

    public static boolean am_i_wilson(double n) {
        if (!isPrime(n)) {
            return false;
        }
        BigDecimal nr = new BigDecimal(n);
        return BigDecimal.ZERO.equals(factorial(nr.substract(BigDecimal.ONE))
                .add(BigDecimal.ONE)
                .divide(nr.multiply(nr))
                .remainder());
    }

    private static BigDecimal factorial(BigDecimal number) {
        if (BigDecimal.ZERO.equals(number)) {
            return BigDEcimal.ONE;
        }
        return number.multiply(factorial(number.subtract(BigDEcimal.ONE)));
    }

    private static boolean isPrime(double number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}