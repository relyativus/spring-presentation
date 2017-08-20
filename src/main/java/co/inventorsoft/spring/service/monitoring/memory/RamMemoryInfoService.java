package co.inventorsoft.spring.service.monitoring.memory;

import co.inventorsoft.spring.model.monitoring.memory.RamMemoryInfo;
import co.inventorsoft.spring.service.logger.Logger;
import org.springframework.stereotype.Component;

@Component
public class RamMemoryInfoService {

    private final Logger logger;

    public RamMemoryInfoService(Logger logger) {
        this.logger = logger;
    }

    public RamMemoryInfo getInfo() {
        final Runtime runtime = Runtime.getRuntime();
        final RamMemoryInfo memoryInfo = new RamMemoryInfo(runtime.totalMemory(), runtime.freeMemory());
        logger.log("RamMemoryInfoService", memoryInfo);
        return memoryInfo;
    }
}
