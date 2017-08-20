package co.inventorsoft.spring.ui;

import co.inventorsoft.spring.model.format.CSVDataBuilder;
import co.inventorsoft.spring.model.format.JSONDataBuilder;
import co.inventorsoft.spring.model.monitoring.disk.DiskMemoryInfo;
import co.inventorsoft.spring.model.monitoring.memory.RamMemoryInfo;
import co.inventorsoft.spring.service.monitoring.disk.DiskInfoService;
import co.inventorsoft.spring.service.monitoring.memory.RamMemoryInfoService;
import lombok.SneakyThrows;

public class ConsoleUI {

    private RamMemoryInfoService ramMemoryInfoService = new RamMemoryInfoService();

    private DiskInfoService diskInfoService = new DiskInfoService();

    @SneakyThrows
    public void run() {
        printInfo("Memory information: ");
        final RamMemoryInfo memoryInfo = ramMemoryInfoService.getInfo();
        final CSVDataBuilder ramInfoBuilder = new CSVDataBuilder();
        memoryInfo.export(ramInfoBuilder);
        System.out.println(ramInfoBuilder.build());

        printInfo("Disk information: ");
        final DiskMemoryInfo info = diskInfoService.getInfo();
        final CSVDataBuilder diskInfoBuilder = new CSVDataBuilder();
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
