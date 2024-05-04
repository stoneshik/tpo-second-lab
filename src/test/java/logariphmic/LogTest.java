package logariphmic;

import lab.function.logariphmic.Ln;
import lab.function.logariphmic.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogTest {
    private final BigDecimal PRECISION = new BigDecimal("0.0001");
    @Mock private Ln mockLn;
    @Spy private Ln spyLn;

    @Test
    public void checkCallFunction() {
        final Log log = new Log(spyLn, 5);
        log.calculate(new BigDecimal("4.0"), new BigDecimal("0.001"));
        verify(
            spyLn,
            atLeastOnce()
        ).calculate(
            any(BigDecimal.class),
            any(BigDecimal.class)
        );
    }

    @Test
    public void checkCalculationWithLnMock() {
        BigDecimal arg = new BigDecimal("10.0");
        when(
            mockLn.calculate(
                eq(new BigDecimal("10.0")),
                any(BigDecimal.class)
            )
        ).thenReturn(new BigDecimal("2.302584851"));
        when(
            mockLn.calculate(
                eq(new BigDecimal(5)),
                any(BigDecimal.class)
            )
        ).thenReturn(new BigDecimal("1.6094379"));
        Log log = new Log(mockLn, 5);
        BigDecimal expected = new BigDecimal("1.430676");
        assertEquals(
            expected,
            log.calculate(arg, new BigDecimal("0.000001"))
        );
    }

    @Test
    public void checkNotCalculationForZero() {
        Log log = new Log(5);
        assertThrows(
            ArithmeticException.class,
            () -> log.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Log log = new Log(5);
        assertEquals(
            ZERO.setScale(PRECISION.scale(), HALF_EVEN),
            log.calculate(ONE, PRECISION)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForCheckCalculation")
    public void checkCalculationForPositive(BigDecimal x, BigDecimal expected) {
        Log log = new Log(5);
        assertEquals(
            expected,
            log.calculate(x, PRECISION)
        );
    }

    private static Stream<Arguments> provideParamsForCheckCalculation() {
        return Stream.of(
            Arguments.of(new BigDecimal("0.4"), new BigDecimal("-0.5694")),
            Arguments.of(new BigDecimal("1.2"), new BigDecimal("0.1134"))
        );
    }
}
