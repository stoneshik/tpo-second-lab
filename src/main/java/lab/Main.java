package lab;

import lab.function.FunctionSystem;
import lab.function.logariphmic.Ln;
import lab.function.logariphmic.Log;
import lab.function.trigonometric.*;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        Cos cos = new Cos();
        CsvWriter.write(
            "csv/cos.csv",
            cos,
            new BigDecimal("-5.0"),
            new BigDecimal("5.0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.0000000001"));
        Sin sin = new Sin();
        CsvWriter.write(
            "csv/sin.csv",
            sin,
            new BigDecimal("-5.0"),
            new BigDecimal("5.0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.0000000001")
        );
        Tan tan = new Tan();
        CsvWriter.write(
            "csv/tan.csv",
            tan,
            new BigDecimal("-5.0"),
            new BigDecimal("5.0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.000000001")
        );
        Cot cot = new Cot();
        CsvWriter.write(
            "csv/cot.csv",
            cot,
            new BigDecimal("-5.01"),
            new BigDecimal("5.0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.000000001")
        );
        Sec sec = new Sec();
        CsvWriter.write(
            "csv/sec.csv",
            sec,
            new BigDecimal("-5.0"),
            new BigDecimal("5.0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.000000001")
        );
        Ln ln = new Ln();
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal("0.1"),
                new BigDecimal("10.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
        Log log2 = new Log(2);
        CsvWriter.write(
                "csv/log2.csv",
                log2,
                new BigDecimal("0.1"),
                new BigDecimal("10.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
        Log log3 = new Log(3);
        CsvWriter.write(
                "csv/log3.csv",
                log3,
                new BigDecimal("0.1"),
                new BigDecimal("10.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
        Log log5 = new Log(5);
        CsvWriter.write(
                "csv/log5.csv",
                log5,
                new BigDecimal("0.1"),
                new BigDecimal("10.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
        Log log10 = new Log(10);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                new BigDecimal("0.1"),
                new BigDecimal("10.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
        FunctionSystem func = new FunctionSystem();
        CsvWriter.write(
                "csv/function_system.csv",
                func,
                new BigDecimal("-4.01"),
                new BigDecimal("4.0"),
                new BigDecimal("0.1"),
                new BigDecimal("0.000000001")
        );
    }
}