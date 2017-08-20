package co.inventorsoft.spring.model.base;

/**
 * Convenient builder to create representation of your data in different formats.
 * Implementation of this interface should care about format specific stuff like
 * delimiters and etc.
 *
 * @see co.inventorsoft.spring.model.format.JSONDataBuilder
 */
public interface FormattedDataBuilder {

    /**
     * Creates new row into result document and returns
     * builder to create row schema. Actual row will be inserted into
     * result document only when you call {@link RowBuilder#insertRow()}
     *
     * @return builder to create schema for row
     */
    RowBuilder createRow();

    /**
     * Builds final representation of the document
     *
     * @return final representation of the document
     */
    String build();
}
