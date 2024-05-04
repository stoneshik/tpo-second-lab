package trigonometry;

import lab.function.trigonometric.Cos;
import lab.function.trigonometric.Sec;
import lab.function.trigonometric.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecTest {
    private final BigDecimal PI = new BigDecimal(Math.PI);
    private static final BigDecimal PRECISION = new BigDecimal("0.0001");
    @Mock private Cos mockCos;
    @Spy
    private Sin spySin;

    @Test
    public void checkCallCosFunction() {
        Cos cos = new Cos(spySin);
        Cos spyCos = spy(cos);
        Sec sec = new Sec(spyCos);
        sec.calculate(ZERO, PRECISION);
        verify(
            spyCos,
            atLeastOnce()
        ).calculate(
            any(BigDecimal.class),
            any(BigDecimal.class)
        );
    }

    @Test
    public void checkCalculationWithMockCos() {
        BigDecimal arg = new BigDecimal("5.0");
        when(
            mockCos.calculate(
                eq(arg),
                any(BigDecimal.class)
            )
        ).thenReturn(
            new BigDecimal("0.28366218")
        );
        Sec sec = new Sec(mockCos);
        BigDecimal expectedResult = new BigDecimal("3.5");
        assertEquals(
            expectedResult.setScale(PRECISION.scale(), HALF_EVEN),
            sec.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForZero() {
        Sec sec = new Sec();
        assertEquals(
            ONE.setScale(PRECISION.scale(), HALF_EVEN),
            sec.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Sec sec = new Sec();
        BigDecimal expected = new BigDecimal("1.9");
        assertEquals(
            expected.setScale(PRECISION.scale(), HALF_EVEN),
            sec.calculate(ONE, PRECISION)
        );
    }

    @Test
    public void checkNotCalculationForPiDividedByTwo() {
        Sec sec = new Sec();
        BigDecimal arg = PI.divide(new BigDecimal("2.0"), HALF_EVEN);
        assertThrows(
            ArithmeticException.class,
            () -> sec.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForPeriod() {
        Sec sec = new Sec();
        BigDecimal expected = new BigDecimal("-2.4");
        BigDecimal x = new BigDecimal("-2.0");
        assertEquals(
            expected.setScale(PRECISION.scale(), HALF_EVEN),
            sec.calculate(x, PRECISION)
        );
        assertEquals(
            expected.setScale(PRECISION.scale(), HALF_EVEN),
            sec.calculate(x.add(new BigDecimal("2.0").multiply(PI)), PRECISION)
        );
    }
}
