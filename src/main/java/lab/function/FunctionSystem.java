package lab.function;

import lab.function.logariphmic.Log;
import lab.function.trigonometric.Cot;
import lab.function.trigonometric.Sec;
import lab.function.trigonometric.Tan;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

/**
 * x <= 0 : (((((cot(x) ^ 2) ^ 3) ^ 3) + (tan(x) * sec(x))) ^ 3)
 * x > 0 : (((((log_5(x) - log_2(x)) + (log_5(x) ^ 2)) / log_5(x)) * (log_2(x) - log_10(x))) / (log_5(x) + log_3(x)))
 */
public class FunctionSystem extends SeriesExpansionFunction {
    private final Cot cot;
    private final Tan tan;
    private final Sec sec;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public FunctionSystem() {
        this.cot = new Cot();
        this.tan = new Tan();
        this.sec = new Sec();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
        this.log10 = new Log(10);
    }

    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
        BigDecimal correctedX = x.remainder(
            new BigDecimal(Math.PI).multiply(new BigDecimal(2))
        );
        if (x.compareTo(ZERO) == 0) {
            return null;
        } else if (x.compareTo(ZERO) <= 0) {
            BigDecimal cotValue = cot.calculate(correctedX, precision);
            BigDecimal tanValue = tan.calculate(correctedX, precision);
            BigDecimal secValue = sec.calculate(correctedX, precision);
            return cotValue
                .pow(2).pow(3).pow(3)
                .add(
                    tanValue
                    .multiply(secValue)
                    .pow(3)
                ).setScale(precision.scale(), HALF_EVEN);
        }
        BigDecimal log2Value = log2.calculate(correctedX, precision);
        BigDecimal log3Value = log3.calculate(correctedX, precision);
        BigDecimal log5Value = log5.calculate(correctedX, precision);
        BigDecimal log10Value = log10.calculate(correctedX, precision);
        return
            (
                log5Value.subtract(log2Value)
                .add(log5Value.pow(2))
                .divide(log5Value, HALF_EVEN)
            ).multiply(
                log2Value.subtract(log10Value)
            ).divide(
                log5Value.add(log3Value), HALF_EVEN
            )
            .setScale(precision.scale(), HALF_EVEN);
    }
}
