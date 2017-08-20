package co.inventorsoft.spring.service.logger;

import co.inventorsoft.spring.model.base.Exportable;
import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Logger {

    private FileOutputStream fos;

    private FormattedDataBuilderFactory formattedDataBuilderFactory;

    public Logger(@Value("${application.logger.directory}") final String directory,
                  final FormattedDataBuilderFactory formattedDataBuilderFactory) {
        this.formattedDataBuilderFactory = formattedDataBuilderFactory;
        openLogFile(directory);
    }

    @SneakyThrows
    public void log(final String serviceName, final Exportable exportable) {
        final FormattedDataBuilder builder = formattedDataBuilderFactory.create();
        exportable.export(builder);
        final String data = builder.build();
        fos.write(String.format("%s:%s\n", serviceName, data).getBytes());
        fos.flush();
    }

    @SneakyThrows
    public void closeConnection() {
        fos.close();
    }

    private void openLogFile(String directory) {
        try {
            fos = new FileOutputStream(directory + "\\output.log", true);
        } catch (IOException e) {
            System.out.println("Cannot create file for logs. Reason: " + e.getMessage());
        }
    }
}
