package lab.function.trigonometric;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class Sec extends SeriesExpansionFunction {
    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        checkArgumentsForCalculation(x, precision);
        BigDecimal cosValue = cos.calculate(x, precision);
        if (cosValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(String.format("Function for x value %s doesn't exist", x));
        }
        BigDecimal result = new BigDecimal("1.0").divide(cosValue, HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
