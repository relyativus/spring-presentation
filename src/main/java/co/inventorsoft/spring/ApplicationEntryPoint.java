package co.inventorsoft.spring;

import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;
import co.inventorsoft.spring.model.format.csv.CSVFormatterFactory;
import co.inventorsoft.spring.service.logger.Logger;
import co.inventorsoft.spring.service.monitoring.disk.DiskInfoService;
import co.inventorsoft.spring.service.monitoring.memory.RamMemoryInfoService;
import co.inventorsoft.spring.ui.ConsoleUI;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        final FormattedDataBuilderFactory formatterFactory = new CSVFormatterFactory();
        final Logger applicationLogger = new Logger("log", formatterFactory);
        final RamMemoryInfoService ramMemoryInfoService = new RamMemoryInfoService(applicationLogger);
        final DiskInfoService diskInfoService = new DiskInfoService(applicationLogger);

        final ConsoleUI consoleUI = new ConsoleUI(ramMemoryInfoService, diskInfoService, formatterFactory);
        consoleUI.run();
    }
}
