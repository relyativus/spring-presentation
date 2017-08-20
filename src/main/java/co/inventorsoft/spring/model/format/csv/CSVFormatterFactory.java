package co.inventorsoft.spring.model.format.csv;

import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;

/**
 * Implementation of {@link FormattedDataBuilderFactory} for CSV format
 */
public class CSVFormatterFactory implements FormattedDataBuilderFactory {

    @Override
    public FormattedDataBuilder create() {
        return new CSVDataBuilder();
    }
}
