package co.inventorsoft.spring.service.logger;

import co.inventorsoft.spring.model.base.Exportable;
import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Component
public class Logger {

    private FileOutputStream fos;

    private FormattedDataBuilderFactory formattedDataBuilderFactory;

    private String directory;

    public Logger(@Value("${application.logger.directory}") final String directory,
                  final FormattedDataBuilderFactory formattedDataBuilderFactory) {
        this.directory = directory;
        this.formattedDataBuilderFactory = formattedDataBuilderFactory;
    }

    @SneakyThrows
    public void log(final String serviceName, final Exportable exportable) {
        final FormattedDataBuilder builder = formattedDataBuilderFactory.create();
        exportable.export(builder);
        final String data = builder.build();
        fos.write(String.format("%s:%s\n", serviceName, data).getBytes());
        fos.flush();
    }

    @PreDestroy
    @SneakyThrows
    public void closeConnection() {
        log.info("Closing system monitor file ...");
        fos.close();
    }

    @PostConstruct
    private void openLogFile() {
        log.info("Initializing system monitor files under {} directory", directory);
        try {
            fos = new FileOutputStream(directory + "\\output.log", true);
        } catch (IOException e) {
            log.warn("Cannot create file for logs. Reason: ", e);
        }
    }
}
