package co.inventorsoft.spring.service.monitoring.disk;

import co.inventorsoft.spring.model.monitoring.disk.DiskMemoryInfo;
import co.inventorsoft.spring.model.monitoring.disk.DiskRootInfo;
import co.inventorsoft.spring.service.logger.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiskInfoService {

    private Logger logger;

    public DiskInfoService(Logger logger) {
        this.logger = logger;
    }

    public DiskMemoryInfo getInfo() throws IOException {
        final Iterable<FileStore> fileStores = FileSystems.getDefault().getFileStores();
        final List<DiskRootInfo> rootesInfo = new ArrayList<>();
        for (final FileStore fileStore : fileStores) {
            final DiskRootInfo rootInfo = new DiskRootInfo(fileStore.name(), fileStore.getUsableSpace());
            rootesInfo.add(rootInfo);
        }

        final DiskMemoryInfo diskMemoryInfo = new DiskMemoryInfo(rootesInfo);
        logger.log("DiskInfoService", diskMemoryInfo);
        return diskMemoryInfo;
    }

}
