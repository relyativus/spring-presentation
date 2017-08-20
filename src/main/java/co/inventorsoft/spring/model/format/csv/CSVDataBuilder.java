package co.inventorsoft.spring.model.format.csv;

import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.RowBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link FormattedDataBuilder} for CSV format
 */
public class CSVDataBuilder implements FormattedDataBuilder {
    private final StringBuilder csv = new StringBuilder();

    private final List<String> header = new ArrayList<>();

    private boolean isHeaderConstructed = false;

    CSVDataBuilder() { }

    public RowBuilder createRow() {
        return new CsvRowBuilder(this, isHeaderConstructed);
    }

    public String build() {
        csv.insert(0, CsvRowBuilder.formatColumn(header));
        return csv.toString();
    }

    void addColumns(final List<String> columns) {
        header.addAll(columns);
        isHeaderConstructed = true;
    }

    void addValues(final String formattedColumn) {
        this.csv.append(formattedColumn);
    }

    /**
     * Responsible to build csv rows as well as keep track of header
     */
    private static class CsvRowBuilder implements RowBuilder {
        private static final String QUOTES_WRAP = "\"%s\"";

        private final CSVDataBuilder CSVDataBuilder;

        private final List<String> values;

        private final boolean isHeaderConstructed;

        private List<String> header;

        CsvRowBuilder(final CSVDataBuilder dataBuilder,
                      final boolean isHeaderConstructed) {
            this.isHeaderConstructed = isHeaderConstructed;
            if (!isHeaderConstructed) {
                this.header = new ArrayList<>();
            }
            this.CSVDataBuilder = dataBuilder;
            this.values = new ArrayList<>();
        }

        public RowBuilder addColumn(final String name, final String value) {
            if (!isHeaderConstructed) {
                header.add(name);
            }
            values.add(value);
            return this;
        }

        public FormattedDataBuilder insertRow() {
            if (!isHeaderConstructed) {
                CSVDataBuilder.addColumns(header);
            }
            CSVDataBuilder.addValues(formatColumn(values));
            return CSVDataBuilder;
        }

        static String formatColumn(final List<String> values) {
            final String formattedColumn = values.stream().map(column -> String.format(QUOTES_WRAP, column))
                    .collect(Collectors.joining(", "));
            return formattedColumn + "\n";
        }
    }
}
