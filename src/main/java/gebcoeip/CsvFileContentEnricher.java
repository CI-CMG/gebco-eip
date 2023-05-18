package gebcoeip;

import gebcoeip.json.FileProcessingMessage;
import gebcoeip.json.DataPoint;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

public class CsvFileContentEnricher {

    private enum HEADERS {
        TIMESTAMP,LONGITUDE,LATITUDE,DEPTH
    }

    public void setPointData(FileProcessingMessage message) throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(message.getDataFile()), StandardCharsets.UTF_8)) {
            Iterable<CSVRecord> rows = CSVFormat.DEFAULT.withHeader(HEADERS.class).withSkipHeaderRecord().parse(reader);
            for (CSVRecord record : rows) {
                message.getDataPoints().add(recordToDataPoint(record, message.getFileId()));
            }
        }
    }

    private DataPoint recordToDataPoint(CSVRecord record, String fileId) {
        DataPoint dataPoint = new DataPoint();
        dataPoint.setFileId(fileId);
        dataPoint.setDateTime(Instant.parse(record.get(HEADERS.TIMESTAMP)));
        dataPoint.setLongitude(Double.parseDouble(record.get(HEADERS.LONGITUDE)));
        dataPoint.setLatitude(Double.parseDouble(record.get(HEADERS.LATITUDE)));
        dataPoint.setDepth(Double.parseDouble(record.get(HEADERS.DEPTH)));
        return dataPoint;
    }
}
