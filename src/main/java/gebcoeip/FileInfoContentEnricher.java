package gebcoeip;

import gebcoeip.json.FileProcessingMessage;
import org.apache.camel.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileInfoContentEnricher {

    public FileProcessingMessage createFileInfoMessage(File metadataFile) throws IOException {

        String fileId = metadataFile.getName().replaceAll("\\.md\\.json$", "");
        Path dataFile = findDataFile(metadataFile, fileId);
        FileProcessingMessage message = new FileProcessingMessage();
        message.setFileId(fileId);
        message.setDataFile(dataFile.toAbsolutePath().normalize().toString());
        message.setMetadataFile(metadataFile.toPath().toAbsolutePath().normalize().toString());
        message.setType(FileUtil.onlyExt(dataFile.getFileName().toString()));
        return message;

    }

    private Path findDataFile(File metadataFile, String fileId) throws IOException {

        Path parent = metadataFile.toPath().getParent();
        try (Stream<Path> files = Files.list(parent)){
            return files.filter(file -> file.getFileName().toString().startsWith(fileId) && !file.getFileName().equals(metadataFile.toPath().getFileName()))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Unable to find datafile for " + metadataFile));
        }
    }
}
