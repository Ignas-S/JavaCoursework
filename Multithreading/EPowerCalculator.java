import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EPowerCalculator implements Runnable, Serializable {

    private static int scale;
    private static int powerX;
    private static CustomWriter writer;

    EPowerCalculator(CustomWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        BigDecimal result = eTaylorSeries(new BigDecimal(powerX));
        long time2 = System.currentTimeMillis();
        writer.printResult(result, ((double)(time2-time)/1000), "e^" + powerX, scale);
    }

    private static BigDecimal getFactorial (int number) {
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigDecimal(i));
        }
        return factorial;
    }

    public BigDecimal eTaylorSeries(BigDecimal x) {
        BigDecimal result = new BigDecimal(1);
        BigDecimal factorial = new BigDecimal(1);
        BigDecimal temp = new BigDecimal(2);
        BigDecimal xiterator = x;
        int factorialindex = 1;
        while(temp.compareTo(BigDecimal.ZERO) == 1) {
            temp = x.divide(factorial, scale, BigDecimal.ROUND_HALF_EVEN);
            result = result.add(temp);
            x = x.multiply(xiterator);
            factorialindex++;
            factorial = getFactorial(factorialindex);
        }
        return result;
    }


    public static int getScale() {
        return scale;
    }

    public static void setScale(int scale) {
        EPowerCalculator.scale = scale;
    }

    public static int getPowerX() {
        return powerX;
    }

    public static void setPowerX(int powerX) {
        EPowerCalculator.powerX = powerX;
    }

}
