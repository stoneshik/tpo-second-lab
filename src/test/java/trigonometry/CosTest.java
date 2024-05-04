package trigonometry;

import lab.function.trigonometric.Cos;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CosTest {
    private final BigDecimal PI = new BigDecimal(Math.PI);
    private final BigDecimal PRECISION = new BigDecimal("0.0001");
    @Mock private Sin mockSin;
    @Spy private Sin spySin;

    @Test
    public void checkCallSinFunction() {
        Cos cos = new Cos(spySin);
        cos.calculate(new BigDecimal(6), new BigDecimal("0.001"));
        verify(
            spySin, atLeastOnce()
        ).calculate(
            any(BigDecimal.class),
            any(BigDecimal.class)
        );
    }

    @Test
    public void checkCalculationWithMockSin() {
        BigDecimal arg = new BigDecimal("5.0");
        BigDecimal correctedArg = PI
            .divide(new BigDecimal("2.0"), HALF_EVEN)
            .subtract(arg);
        BigDecimal sinResult = new BigDecimal("-0.958925");
        when(
            mockSin.calculate(eq(correctedArg), any(BigDecimal.class))
        ).thenReturn(sinResult);
        Cos cos = new Cos(mockSin);
        assertEquals(
            sinResult,
            cos.calculate(arg, new BigDecimal("0.000001"))
        );
    }

    @Test
    public void checkCalculationForZero() {
        Cos cos = new Cos();
        assertEquals(
            ONE, cos.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Cos cos = new Cos();
        BigDecimal expected = new BigDecimal("0.5403");
        assertEquals(expected, cos.calculate(ONE, PRECISION));
    }

    @Test
    public void checkCalculationForPiDividedByTwo() {
        Cos cos = new Cos();
        BigDecimal arg = PI.divide(new BigDecimal("2.0"), HALF_EVEN);
        assertEquals(
            ZERO.setScale(PRECISION.scale(), HALF_EVEN),
            cos.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForPeriod() {
        Cos cos = new Cos();
        BigDecimal expected = new BigDecimal("-0.9900");
        BigDecimal x = new BigDecimal("-3.0");
        assertEquals(
            expected,
            cos.calculate(x, PRECISION)
        );
        assertEquals(
            expected,
            cos.calculate(x.add(new BigDecimal("2.0").multiply(PI)), PRECISION)
        );
    }
}