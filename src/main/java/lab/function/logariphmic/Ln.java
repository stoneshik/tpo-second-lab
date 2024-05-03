package lab.function.logariphmic;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Ln extends SeriesExpansionFunction {
    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        checkArgumentsForCalculation(x, precision);
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(String.format("Function for x value %s doesn't exist", x));
        }
        if (x.compareTo(ONE) == 0) {
            return ZERO;
        }
        final BigDecimal PRECISION_SCALE = BigDecimal.valueOf(0.1)
                .pow(precision.scale());
        final BigDecimal X_MODULE_SUB_ONE = x.subtract(ONE).abs();
        int i = 1;
        BigDecimal currentX = BigDecimal.ZERO;
        BigDecimal previousX;
        BigDecimal delta;
        if (X_MODULE_SUB_ONE.compareTo(ONE) <= 0) {
            do {
                previousX = currentX;
                currentX = currentX.add(
                    (BigDecimal.valueOf(-1).pow(i - 1)
                        .multiply(x.subtract(ONE).pow(i))
                    ).divide(BigDecimal.valueOf(i), precision.scale(), HALF_UP)
                );
                delta = (previousX.subtract(currentX)).abs();
                i++;
            } while (PRECISION_SCALE.compareTo(delta) < 0 && i < MAX_ITERATIONS);
            return currentX.add(previousX).divide(BigDecimal.valueOf(2), HALF_EVEN);
        }
        do {
            previousX = currentX;
            currentX = currentX.add(
                (BigDecimal.valueOf(-1)
                    .pow(i - 1)
                    .divide((x.subtract(ONE)).pow(i), precision.scale(), HALF_UP)
                ).divide(BigDecimal.valueOf(i), precision.scale(), HALF_UP)
            );
            delta = (previousX.subtract(currentX)).abs();
            i++;
        } while (PRECISION_SCALE.compareTo(delta) < 0 && i < MAX_ITERATIONS);
        currentX = currentX.add(
            calculate(x.subtract(ONE), precision)
        );
        return currentX.setScale(precision.scale(), HALF_EVEN);
    }
}
