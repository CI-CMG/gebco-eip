package gebcoeip.json;

public class AggregationMessage {

    private String fileId;
    private FileProcessingMessage message;
    private int pointSaveCount;
    private DataPoint dataPoint;
    private boolean complete;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public DataPoint getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    public FileProcessingMessage getMessage() {
        return message;
    }

    public void setMessage(FileProcessingMessage message) {
        this.message = message;
    }

    public int getPointSaveCount() {
        return pointSaveCount;
    }

    public void setPointSaveCount(int pointSaveCount) {
        this.pointSaveCount = pointSaveCount;
    }
}
