import java.io.*;
import java.math.BigDecimal;

public class PiCalculator implements Runnable, Serializable {

    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private static int digits;
    private static CustomWriter writer;

    public PiCalculator(CustomWriter writer) {
        this.writer = writer;
    }


    @Override
    public void run() {

        long time = System.currentTimeMillis();
        BigDecimal result = computePi(digits);
        long time2 = System.currentTimeMillis();
        writer.printResult(result, ((double)(time2-time)/1000), "pi", digits);

    }

    /**
     * Compute the value of pi to the specified number of
     * digits after the decimal point.
     */
    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 +
     *     (x^9)/9 ...
     */
    public static BigDecimal arctan(int inverseX, int scale)
    {
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 = BigDecimal.valueOf(inverseX * inverseX);

        numer = BigDecimal.ONE.divide(invX, scale, roundingMode);

        result = numer;
        int i = 1;
        do {
            numer = numer.divide(invX2, scale, roundingMode);
            int denom = 2 * i + 1;
            term = numer.divide(BigDecimal.valueOf(denom), scale, roundingMode);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }

    public static int getScale() {
        return digits;
    }

    public static void setScale(int digits) {
        PiCalculator.digits = digits;
    }
}
