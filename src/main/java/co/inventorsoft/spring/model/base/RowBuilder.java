package co.inventorsoft.spring.model.base;

/**
 * Defines a way to create row schema in different formats.
 */
public interface RowBuilder {

    /**
     * Adds new column represented by name and value
     *
     * @param name  column name
     * @param value column value
     * @return returns the same row builder to allow method chaining
     */
    RowBuilder addColumn(final String name, final String value);

    /**
     * Commits row insertion in the parent builder
     *
     * @return parent document builder
     */
    FormattedDataBuilder insertRow();
}
