package logariphmic;

import lab.function.logariphmic.Ln;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private final BigDecimal PRECISION = new BigDecimal("0.00001");

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

    @ParameterizedTest
    @MethodSource("provideParamsForCheckCalculation")
    public void checkCalculation(BigDecimal x, BigDecimal expected) {
        Ln ln = new Ln();
        assertEquals(
            expected,
            ln.calculate(x, PRECISION)
        );
    }

    private static Stream<Arguments> provideParamsForCheckCalculation() {
        return Stream.of(
            Arguments.of(new BigDecimal("0.1"), new BigDecimal("-2.30248")),
            Arguments.of(new BigDecimal("2.0"), new BigDecimal("0.69315"))
        );
    }
}
