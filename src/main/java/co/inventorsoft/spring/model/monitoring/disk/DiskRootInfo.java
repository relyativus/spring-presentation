package co.inventorsoft.spring.model.monitoring.disk;

import lombok.Value;

@Value
public class DiskRootInfo {

    private String label;

    private long freeSpace;
}
