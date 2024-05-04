package trigonometry;

import lab.function.trigonometric.Cos;
import lab.function.trigonometric.Cot;
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
public class CotTest {
    private final BigDecimal PI = new BigDecimal(Math.PI);
    private static final BigDecimal PRECISION = new BigDecimal("0.0001");
    @Mock private Sin mockSin;
    @Mock private Cos mockCos;
    @Spy private Sin spySin;

    @Test
    public void checkCallSinAndCosFunctions() {
        Cos cos = new Cos(spySin);
        Cos spyCos = spy(cos);
        Cot cot = new Cot(spySin, spyCos);
        cot.calculate(ONE, PRECISION);
        verify(
            spySin,
            atLeastOnce()
        ).calculate(
            any(BigDecimal.class),
            any(BigDecimal.class)
        );
        verify(
            spyCos,
            atLeastOnce()
        ).calculate(
            any(BigDecimal.class),
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
        Cot cot = new Cot(mockSin, mockCos);
        BigDecimal expectedResult = new BigDecimal("-0.2958");
        assertEquals(
            expectedResult,
            cot.calculate(arg, PRECISION)
        );
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
        Cot cot = new Cot(mockSin, new Cos());
        BigDecimal expectedResult = new BigDecimal("-0.2959");
        assertEquals(
            expectedResult,
            cot.calculate(arg, PRECISION)
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
        Cot cot = new Cot(new Sin(), mockCos);
        BigDecimal expectedResult = new BigDecimal("-0.2958");
        assertEquals(
            expectedResult,
            cot.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkNotCalculationForZero() {
        Cot cot = new Cot();
        assertThrows(
            ArithmeticException.class,
            () -> cot.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Cot cot = new Cot();
        BigDecimal expected = new BigDecimal("0.6421");
        assertEquals(
            expected,
            cot.calculate(ONE, PRECISION)
        );
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
        Cot cot = new Cot();
        BigDecimal expected = new BigDecimal("7.0163");
        BigDecimal x = new BigDecimal("-3.0");
        assertEquals(
            expected,
            cot.calculate(x, PRECISION)
        );
        assertEquals(
            expected,
            cot.calculate(x.add(PI), PRECISION)
        );
    }
}