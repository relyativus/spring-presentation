package co.inventorsoft.spring.model.format;

import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.RowBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link FormattedDataBuilder} for JSON data format
 */
public class JSONDataBuilder implements FormattedDataBuilder {

    private final StringBuilder json = new StringBuilder();

    private boolean isFirstRow = true;

    public RowBuilder createRow() {
        return new JSONRowBuilder(this);
    }

    public String build() {
        return String.format("[%s]", json.toString());
    }

    void addRow(final String formattedRow) {
        final String row = String.format("{\n %s \n}", formattedRow);
        this.json.append(isFirstRow ? row : String.format(",\n%s", row));
        isFirstRow = false;
    }

    /**
     * Implementation of row builder for JSON format
     */
    private static class JSONRowBuilder implements RowBuilder {

        private final JSONDataBuilder jsonDataBuilder;
        private final List<String> columns;

        JSONRowBuilder(JSONDataBuilder jsonDataBuilder) {
            this.jsonDataBuilder = jsonDataBuilder;
            this.columns = new ArrayList<>();
        }

        public RowBuilder addColumn(String name, String value) {
            columns.add(String.format("\"%s\":\"%s\"", name, value));
            return this;
        }

        public FormattedDataBuilder insertRow() {
            final String formattedColumns = String.join(",\n", columns);
            jsonDataBuilder.addRow(formattedColumns);
            return jsonDataBuilder;
        }
    }
}
