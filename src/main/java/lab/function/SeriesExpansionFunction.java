package lab.function;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

public abstract class SeriesExpansionFunction implements Function {
    protected final int MAX_ITERATIONS = 1000;

    protected void checkArgumentsForCalculation(BigDecimal x, BigDecimal precision) {
        Objects.requireNonNull(x, "x value can't be null");
        Objects.requireNonNull(precision, "Precision value can't be null");
        if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
            throw new ArithmeticException("Precision value must be less than 1 and more than 0");
        }
    }
}
