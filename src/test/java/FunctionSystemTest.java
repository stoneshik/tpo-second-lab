import lab.function.FunctionSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionSystemTest {
    private final BigDecimal PRECISION = new BigDecimal("0.00000001");

    @Test
    public void checkNotAcceptNullArgument() {
        FunctionSystem system = new FunctionSystem();
        assertThrows(
            NullPointerException.class,
            () -> system.calculate(null, PRECISION)
        );
    }

    @Test
    public void checkNotAcceptNullPrecision() {
        FunctionSystem system = new FunctionSystem();
        assertThrows(
            NullPointerException.class,
            () -> system.calculate(new BigDecimal("-2.0"), null)
        );
    }

    @ParameterizedTest
    @MethodSource("illegalPrecisions")
    public void checkNotAcceptIncorrectPrecisions(BigDecimal precision) {
        FunctionSystem system = new FunctionSystem();
        assertThrows(
            ArithmeticException.class,
            () -> system.calculate(new BigDecimal("-2.0"), precision)
        );
    }

    @Test
    public void checkNotAcceptZeroArgument() {
        FunctionSystem system = new FunctionSystem();
        assertNull(system.calculate(ZERO, PRECISION));
    }

    @Test
    public void checkNotAcceptOneArgument() {
        FunctionSystem system = new FunctionSystem();
        assertThrows(
            ArithmeticException.class,
            () -> system.calculate(ONE, PRECISION)
        );
    }

    @Test
    public void checkCalculateForPositiveValue() {
        FunctionSystem system = new FunctionSystem();
        BigDecimal expected = new BigDecimal("-0.21196011");
        assertEquals(
            expected,
            system.calculate(new BigDecimal("5.0"), PRECISION)
        );
    }

    @Test
    public void checkCalculateForNegativeValue() {
        FunctionSystem system = new FunctionSystem();
        BigDecimal expected = new BigDecimal("1656.35228780");
        assertEquals(
            expected,
            system.calculate(new BigDecimal("-5.0"), PRECISION)
        );
    }

    private static Stream<Arguments> illegalPrecisions() {
        return Stream.of(
            Arguments.of(BigDecimal.valueOf(1)),
            Arguments.of(BigDecimal.valueOf(0)),
            Arguments.of(BigDecimal.valueOf(1.01)),
            Arguments.of(BigDecimal.valueOf(-0.01))
        );
    }
}
