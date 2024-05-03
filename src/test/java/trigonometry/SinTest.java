package trigonometry;

import lab.function.trigonometric.Sin;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private final BigDecimal PI = new BigDecimal(Math.PI);
    private final BigDecimal PRECISION = new BigDecimal("0.0001");

    @Test
    public void checkCalculationForZero() {
        Sin sin = new Sin();
        assertEquals(
            ZERO.setScale(4, HALF_EVEN),
            sin.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Sin sin = new Sin();
        BigDecimal expected = new BigDecimal("0.8415");
        assertEquals(expected, sin.calculate(ONE, PRECISION));
    }

    @Test
    public void checkCalculationForPiDividedByTwo() {
        Sin sin = new Sin();
        BigDecimal arg = PI
            .divide(
                new BigDecimal("2.0"),
                HALF_EVEN
            );
        assertEquals(
            ONE.setScale(PRECISION.scale(), HALF_EVEN),
            sin.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForPeriodic() {
        Sin sin = new Sin();
        BigDecimal expected = new BigDecimal("0.0972");
        assertEquals(
            expected,
            sin.calculate(new BigDecimal("-113.0"), PRECISION)
        );
    }
}