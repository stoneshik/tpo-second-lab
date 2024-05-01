package lab.function.trigonometric;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class Cot extends SeriesExpansionFunction {
    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        checkArgumentsForCalculation(x, precision);
        BigDecimal sinValue = sin.calculate(x, precision);
        BigDecimal cosValue = cos.calculate(x, precision);
        if (sinValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(String.format("Function for x value %s doesn't exist", x));
        }
        BigDecimal result = cosValue.divide(sinValue, HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
