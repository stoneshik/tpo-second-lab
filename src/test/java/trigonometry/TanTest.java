package trigonometry;

import lab.function.trigonometric.Cos;
import lab.function.trigonometric.Sin;
import lab.function.trigonometric.Tan;
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
public class TanTest {
    private final BigDecimal PI = new BigDecimal(Math.PI);
    private static final BigDecimal PRECISION = new BigDecimal("0.0001");
    @Mock private Sin mockSin;
    @Mock private Cos mockCos;
    @Spy private Sin spySin;

    @Test
    public void checkCallSinAndCosFunctions() {
        Cos cos = new Cos(spySin);
        Cos spyCos = spy(cos);
        Tan tan = new Tan(spySin, spyCos);
        tan.calculate(ZERO, PRECISION);
        verify(
            spySin,
            atLeastOnce()).calculate(any(BigDecimal.class),
            any(BigDecimal.class)
        );
        verify(
            spyCos,
            atLeastOnce()).calculate(any(BigDecimal.class),
            any(BigDecimal.class)
        );
    }

    @Test
    public void checkCalculationWithMockSinAndMockCos() {
        BigDecimal arg = new BigDecimal("5.0");
        when(
            mockSin.calculate(
                eq(arg),
                any(BigDecimal.class)
            )
        ).thenReturn(
            new BigDecimal("-0.95892427")
        );
        when(
            mockCos.calculate(
                eq(arg),
                any(BigDecimal.class)
            )
        ).thenReturn(
            new BigDecimal("0.28366218")
        );
        Tan tan = new Tan(mockSin, mockCos);
        BigDecimal expectedResult = new BigDecimal("-3.3805");
        assertEquals(expectedResult, tan.calculate(arg, PRECISION));
    }

    @Test
    public void checkCalculationWithMockSin() {
        BigDecimal arg = new BigDecimal("5.0");
        when(
            mockSin.calculate(
                eq(arg),
                any(BigDecimal.class)
            )
        ).thenReturn(
            new BigDecimal("-0.95892427")
        );
        Tan tan = new Tan(mockSin, new Cos());
        BigDecimal expectedResult = new BigDecimal("-3.3801");
        assertEquals(expectedResult, tan.calculate(arg, PRECISION));
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
        Tan tan = new Tan(new Sin(), mockCos);
        BigDecimal expectedResult = new BigDecimal("-3.3804");
        assertEquals(expectedResult, tan.calculate(arg, PRECISION));
    }

    @Test
    public void checkCalculationForZero() {
        Tan tan = new Tan();
        assertEquals(
            ZERO.setScale(PRECISION.scale(), HALF_EVEN),
            tan.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Tan tan = new Tan();
        BigDecimal expected = new BigDecimal("1.5575");
        assertEquals(expected, tan.calculate(ONE, PRECISION));
    }

    @Test
    public void checkNotCalculationForPiDividedByTwo() {
        Tan tan = new Tan();
        BigDecimal arg = PI.divide(new BigDecimal("2.0"), HALF_EVEN);
        assertThrows(
            ArithmeticException.class,
            () -> tan.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForPeriod() {
        Tan tan = new Tan();
        BigDecimal expected = new BigDecimal("0.1425");
        BigDecimal x = new BigDecimal("-3.0");
        assertEquals(
            expected,
            tan.calculate(x, PRECISION)
        );
        assertEquals(
            expected,
            tan.calculate(x.add(PI), PRECISION)
        );
    }
}