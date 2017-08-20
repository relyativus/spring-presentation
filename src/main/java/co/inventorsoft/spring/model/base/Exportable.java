package co.inventorsoft.spring.model.base;

/**
 * Allows to export inner state of implementation into formate represented
 * by builder
 */
public interface Exportable {

    /**
     * Create exported object schema based on passed builder.
     * You shouldn't call {@link FormattedDataBuilder#build()} method manually, just
     * create your object state using builder's api
     *
     * @param formattedDataBuilder format specific builder
     */
    void export(final FormattedDataBuilder formattedDataBuilder);
}
