package co.inventorsoft.spring.ui;

import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import co.inventorsoft.spring.model.base.FormattedDataBuilderFactory;
import co.inventorsoft.spring.model.monitoring.disk.DiskMemoryInfo;
import co.inventorsoft.spring.model.monitoring.memory.RamMemoryInfo;
import co.inventorsoft.spring.service.monitoring.disk.DiskInfoService;
import co.inventorsoft.spring.service.monitoring.memory.RamMemoryInfoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleUI implements CommandLineRunner {

    private final RamMemoryInfoService ramMemoryInfoService;

    private final DiskInfoService diskInfoService;

    private final FormattedDataBuilderFactory formattedDataBuilderFactory;

    public ConsoleUI(final RamMemoryInfoService ramMemoryInfoService,
                     final DiskInfoService diskInfoService,
                     final FormattedDataBuilderFactory formattedDataBuilderFactory) {
        this.ramMemoryInfoService = ramMemoryInfoService;
        this.diskInfoService = diskInfoService;
        this.formattedDataBuilderFactory = formattedDataBuilderFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        printInfo("Memory information: ");
        final RamMemoryInfo memoryInfo = ramMemoryInfoService.getInfo();
        final FormattedDataBuilder ramInfoBuilder = formattedDataBuilderFactory.create();
        memoryInfo.export(ramInfoBuilder);
        System.out.println(ramInfoBuilder.build());

        printInfo("Disk information: ");
        final DiskMemoryInfo info = diskInfoService.getInfo();
        final FormattedDataBuilder diskInfoBuilder = formattedDataBuilderFactory.create();
        info.export(diskInfoBuilder);
        System.out.println(diskInfoBuilder.build());
    }

    private void printInfo(final String infoMessage) {
        System.out.println();
        System.out.println(infoMessage);
        System.out.println("************************************");
        System.out.println();
    }
}
