package lab.function.trigonometric;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class Sin extends SeriesExpansionFunction {
    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        final BigDecimal PI2 = BigDecimal.valueOf(Math.PI).multiply(BigDecimal.valueOf(2));
        final BigDecimal PRECISION_SCALE = new BigDecimal("0.1").pow(precision.scale());
        int i = 0;
        BigDecimal sum = new BigDecimal(0);
        BigDecimal sumPrevious;
        if (x.compareTo(ZERO) >= 0) {
            while (x.compareTo(PI2) > 0) {
                x = x.subtract(PI2);
            }
        } else if (x.compareTo(ZERO) < 0) {
            while (x.compareTo(PI2) < 0) {
                x = x.add(PI2);
            }
        }
        BigDecimal delta;
        do {
            sumPrevious = sum;
            sum = sum.add(
                BigDecimal.valueOf(1 - (i % 2) * 2)
                .multiply(
                    calculationAccumulator(x, 2 * i + 1)
                )
            );
            delta = sumPrevious.subtract(sum).abs();
            i++;
        } while (PRECISION_SCALE.compareTo(delta) < 0);
        return sum.setScale(precision.scale(), HALF_EVEN);
    }

    private BigDecimal calculationAccumulator(BigDecimal x, int n) {
        BigDecimal accumulator = new BigDecimal(1);
        for (int i = 1; i <= n; i++) {
            accumulator = accumulator.multiply(x.divide(BigDecimal.valueOf(i), HALF_EVEN));
        }
        return accumulator;
    }
}
