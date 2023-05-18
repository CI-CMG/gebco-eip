package gebcoeip.json;

import java.util.ArrayList;
import java.util.List;

public class FileProcessingMessage {

    private String fileId;
    private String metadataFile;
    private String dataFile;
    private String type;
    private final List<DataPoint> dataPoints = new ArrayList<>();


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMetadataFile() {
        return metadataFile;
    }

    public void setMetadataFile(String metadataFile) {
        this.metadataFile = metadataFile;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

}
