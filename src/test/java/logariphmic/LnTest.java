package logariphmic;

import lab.function.logariphmic.Ln;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private static final BigDecimal PRECISION = new BigDecimal("0.00001");

    @Test
    public void checkNotCalculationForZero() {
        Ln ln = new Ln();
        assertThrows(
            ArithmeticException.class,
            () -> ln.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Ln ln = new Ln();
        assertEquals(ZERO, ln.calculate(ONE, PRECISION));
    }

    @Test
    public void checkCalculationForPositive() {
        Ln ln = new Ln();
        BigDecimal expected = new BigDecimal("1.38629");
        assertEquals(
            expected,
            ln.calculate(new BigDecimal(4), PRECISION)
        );
    }
}
