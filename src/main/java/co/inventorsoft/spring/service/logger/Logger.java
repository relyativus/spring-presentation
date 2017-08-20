package co.inventorsoft.spring.service.logger;

import co.inventorsoft.spring.model.base.Exportable;
import co.inventorsoft.spring.model.format.CSVDataBuilder;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.IOException;

public class Logger {

    private FileOutputStream fos;

    public Logger(final String fileName) {
        try {
            fos = new FileOutputStream(fileName, true);
        } catch (IOException e) {
            System.out.println("Cannot create file for logs. Reason: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void log(final String serviceName, final Exportable exportable) {
        final CSVDataBuilder csvDataBuilder = new CSVDataBuilder();
        exportable.export(csvDataBuilder);
        final String data = csvDataBuilder.build();
        fos.write(String.format("%s:%s\n", serviceName, data).getBytes());
        fos.flush();
    }

    @SneakyThrows
    public void closeConnection() {
        fos.close();
    }
}
