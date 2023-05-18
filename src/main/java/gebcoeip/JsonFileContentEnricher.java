package gebcoeip;

import com.fasterxml.jackson.databind.ObjectMapper;
import gebcoeip.json.DataPoint;
import gebcoeip.json.FileProcessingMessage;
import gebcoeip.json.JsonDataFile;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileContentEnricher {

    private ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setPointData(FileProcessingMessage message) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(message.getDataFile()), StandardCharsets.UTF_8)) {
            JsonDataFile dataFile = objectMapper.readValue(reader, JsonDataFile.class);
            for (DataPoint dataPoint : dataFile.getData()) {
                dataPoint.setFileId(message.getFileId());
                message.getDataPoints().add(dataPoint);
            }
        }
    }

}
