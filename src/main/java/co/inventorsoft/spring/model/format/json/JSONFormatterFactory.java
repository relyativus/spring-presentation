package co.inventorsoft.spring.model.format.json;

import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;

public class JSONFormatterFactory implements FormattedDataBuilderFactory {

    @Override
    public FormattedDataBuilder create() {
        return new JSONDataBuilder();
    }
}
