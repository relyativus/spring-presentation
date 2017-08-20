package co.inventorsoft.spring.model.monitoring.memory;

import co.inventorsoft.spring.model.base.Exportable;
import co.inventorsoft.spring.model.base.FormattedDataBuilder;
import lombok.Getter;

@Getter
public class RamMemoryInfo implements Exportable {

    private long totalMemoryInBytes;

    private long freeMemoryInBytes;

    private long consumedMemoryInBytes;

    public RamMemoryInfo(long totalMemoryInBytes, long freeMemoryInBytes) {
        this.totalMemoryInBytes = totalMemoryInBytes;
        this.freeMemoryInBytes = freeMemoryInBytes;
        this.consumedMemoryInBytes = totalMemoryInBytes - freeMemoryInBytes;
    }


    public void export(FormattedDataBuilder formattedDataBuilder) {
        // @formatter:off
        formattedDataBuilder
                .createRow()
                        .addColumn("Total memory(in bytes)", String.valueOf(totalMemoryInBytes))
                        .addColumn("Available memory(in bytes)", String.valueOf(freeMemoryInBytes))
                        .addColumn("ConsumedMemory(in bytes)", String.valueOf(consumedMemoryInBytes))
                .insertRow();
        // @formatter:on

    }
}
