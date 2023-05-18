package gebcoeip;

import gebcoeip.json.FileProcessingMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCleanupProcessor {

    public void cleanup(FileProcessingMessage message) throws IOException {
        if(message.getDataFile() != null) {
            Path dataPath = Paths.get(message.getDataFile());
            Path parent = dataPath.getParent();
            Path newPath = parent.resolve(".camel").resolve(dataPath.getFileName());
            Files.move(dataPath, newPath);
        }
    }
}
