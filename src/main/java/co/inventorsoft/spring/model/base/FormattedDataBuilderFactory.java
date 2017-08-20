package co.inventorsoft.spring.model.base;

/**
 * Responsible for creation format specific builders.
 */
public interface FormattedDataBuilderFactory {

    /**
     * Create format specific builder
     *
     * @return format specific builder
     */
    FormattedDataBuilder create();
}
