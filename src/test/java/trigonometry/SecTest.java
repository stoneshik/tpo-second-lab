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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecTest {
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
        BigDecimal expectedResult = new BigDecimal("3.5000");
        assertEquals(
            expectedResult,
            sec.calculate(arg, PRECISION)
        );
    }

    @Test
    public void checkCalculationForZero() {
        Sec sec = new Sec();
        BigDecimal expected = new BigDecimal("1.0000");
        assertEquals(
            expected,
            sec.calculate(ZERO, PRECISION)
        );
    }

    @Test
    public void checkCalculationForOne() {
        Sec sec = new Sec();
        BigDecimal expected = new BigDecimal("1.9000");
        assertEquals(
            expected,
            sec.calculate(ONE, PRECISION)
        );
    }

    @Test
    public void checkCalculationForPeriodic() {
        Sec sec = new Sec();
        BigDecimal expected = new BigDecimal("-2.2000");
        assertEquals(
            expected,
            sec.calculate(new BigDecimal("134.0"), PRECISION)
        );
    }
}
