import lab.function.SeriesExpansionFunction;
import lab.function.logariphmic.Ln;
import lab.function.logariphmic.Log;
import lab.function.trigonometric.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeriesExpansionFunctionTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000001");

    @ParameterizedTest
    @MethodSource("functions")
    public void checkNotAcceptNullArgument(SeriesExpansionFunction function) {
        assertThrows(
            NullPointerException.class,
            () -> function.calculate(null, DEFAULT_PRECISION)
        );
    }

    @ParameterizedTest
    @MethodSource("functions")
    public void checkNotAcceptNullPrecision(SeriesExpansionFunction function) {
        assertThrows(
            NullPointerException.class,
            () -> function.calculate(ONE, null)
        );
    }

    private static Stream<Arguments> functions() {
        return Stream.of(
            Arguments.of(new Sin()),
            Arguments.of(new Cos()),
            Arguments.of(new Tan()),
            Arguments.of(new Cot()),
            Arguments.of(new Sec()),
            Arguments.of(new Ln()),
            Arguments.of(new Log(2)),
            Arguments.of(new Log(3)),
            Arguments.of(new Log(5)),
            Arguments.of(new Log(10))
        );
    }
}
