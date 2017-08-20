package co.inventorsoft.spring.service.monitoring.memory;

import co.inventorsoft.spring.model.monitoring.memory.RamMemoryInfo;
import co.inventorsoft.spring.service.logger.Logger;

public class RamMemoryInfoService {

    private Logger logger = new Logger("log/ram.log.txt");

    public RamMemoryInfo getInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final RamMemoryInfo memoryInfo = new RamMemoryInfo(runtime.totalMemory(), runtime.freeMemory());
        logger.log("RamMemoryInfoService", memoryInfo);
        return memoryInfo;
    }
}
