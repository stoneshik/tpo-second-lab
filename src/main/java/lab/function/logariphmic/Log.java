package lab.function.logariphmic;

import lab.function.SeriesExpansionFunction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class Log extends SeriesExpansionFunction {
    private final Ln ln;
    private final int base;

    public Log(int base) {
        this.ln = new Ln();
        this.base = base;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        checkArgumentsForCalculation(x, precision);
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(String.format("Function for x value %s doesn't exist", x));
        }
        BigDecimal result = ln.calculate(x, precision)
            .divide(
                ln.calculate(new BigDecimal(base), precision),
                HALF_EVEN
            );
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
