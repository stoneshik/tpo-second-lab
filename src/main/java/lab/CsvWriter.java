package lab;

import lab.function.SeriesExpansionFunction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvWriter {
    public static void write(
            final String filename,
            final SeriesExpansionFunction function,
            final BigDecimal from,
            final BigDecimal to,
            final BigDecimal step,
            final BigDecimal precision
    ) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        final PrintWriter printWriter = new PrintWriter(file);
        for (BigDecimal currentValue = from;
             currentValue.compareTo(to) <= 0;
             currentValue = currentValue.add(step)) {
            printWriter.println(currentValue + "," + function.calculate(currentValue, precision));
        }
        printWriter.close();
    }
}