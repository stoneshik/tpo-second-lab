package lab.function.trigonometric;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class Cos extends SeriesExpansionFunction {
    private final Sin sin;

    public Cos() {
        this.sin = new Sin();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        checkArgumentsForCalculation(x, precision);
        final BigDecimal correctedX = x.remainder(BigDecimal.valueOf(Math.PI).multiply(new BigDecimal(2)));
        if (correctedX.compareTo(ZERO) == 0) {
            return ONE;
        }
        final BigDecimal result =
            sin.calculate(
                BigDecimal.valueOf(Math.PI)
                    .divide(new BigDecimal(2), HALF_EVEN)
                    .subtract(correctedX),
                precision
            );
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
