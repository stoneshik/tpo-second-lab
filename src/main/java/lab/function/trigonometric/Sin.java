package lab.function.trigonometric;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class Sin extends SeriesExpansionFunction {
    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        final double PI2 = Math.PI * 2;
        final BigDecimal PRECISION_SCALE = new BigDecimal("0.1")
                .pow(precision.scale());
        BigDecimal sum = new BigDecimal("0.0");
        double xValue = x.doubleValue();
        int i = 0;
        BigDecimal previousX;
        if (xValue >= 0) {
            while (xValue > PI2) {
                xValue -= PI2;
            }
        } else if (xValue < 0){
            while (xValue < PI2) {
                xValue += PI2;
            }
        }
        do {
            previousX = sum;
            final BigDecimal MINUS_ONE_POW = new BigDecimal(1 - (i % 2) * 2);
            sum = sum.add(
                MINUS_ONE_POW.multiply(
                    calculationAccumulator(xValue, 2 * i + 1)
                )
            );
            i++;
        } while (PRECISION_SCALE.compareTo(previousX.subtract(sum).abs()) < 0);
        return sum.setScale(precision.scale(), HALF_EVEN);
    }

    private BigDecimal calculationAccumulator(double x, int n) {
        BigDecimal accumulator = new BigDecimal("1.0");
        for (int i = 1; i <= n; i++) {
            accumulator = accumulator.multiply(
                new BigDecimal(x / i)
            );
        }
        return accumulator;
    }
}
