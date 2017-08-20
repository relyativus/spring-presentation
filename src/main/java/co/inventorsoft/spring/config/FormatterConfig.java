package co.inventorsoft.spring.config;

import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;
import co.inventorsoft.spring.model.format.csv.CSVFormatterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterConfig {

    @Bean
    public FormattedDataBuilderFactory formattedDataBuilderFactory() {
        return new CSVFormatterFactory();
    }
}
