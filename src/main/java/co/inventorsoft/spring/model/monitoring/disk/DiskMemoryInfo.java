package co.inventorsoft.spring.model.monitoring.disk;

import co.inventorsoft.spring.model.base.Exportable;
import co.inventorsoft.spring.model.base.FormattedDataBuilder;

import java.util.List;

public class DiskMemoryInfo implements Exportable {

    private List<DiskRootInfo> routes;

    public DiskMemoryInfo(List<DiskRootInfo> routes) {
        this.routes = routes;
    }

    public void export(FormattedDataBuilder formattedDataBuilder) {
        for (DiskRootInfo rootInfo : routes) {
            // @formatter:off
            formattedDataBuilder
                .createRow()
                    .addColumn("Disk label", rootInfo.getLabel())
                    .addColumn("Free space", String.valueOf(rootInfo.getFreeSpace()))
                .insertRow();
            // @formatter:on
        }
    }
}
