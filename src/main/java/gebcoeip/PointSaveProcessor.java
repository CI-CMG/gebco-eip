package gebcoeip;

import gebcoeip.database.DataPointEntity;
import gebcoeip.database.DataPointRepository;
import gebcoeip.json.AggregationMessage;
import gebcoeip.json.DataPoint;
import org.springframework.transaction.support.TransactionTemplate;

public class PointSaveProcessor {

    private DataPointRepository repository;
    private TransactionTemplate tx;

    public void setRepository(DataPointRepository repository) {
        this.repository = repository;
    }

    public void setTx(TransactionTemplate tx) {
        this.tx = tx;
    }

    public void savePoint(DataPoint dataPoint) {
        tx.executeWithoutResult(s -> {
            DataPointEntity entity = new DataPointEntity();
            entity.setFileId(dataPoint.getFileId());
            entity.setDateTime(dataPoint.getDateTime());
            entity.setLongitude(dataPoint.getLongitude());
            entity.setLatitude(dataPoint.getLatitude());
            entity.setDepth(dataPoint.getDepth());
            repository.save(entity);
        });
    }

    public AggregationMessage aggregate(DataPoint dataPoint) {
        AggregationMessage aggregationMessage = new AggregationMessage();
        aggregationMessage.setFileId(dataPoint.getFileId());
        aggregationMessage.setDataPoint(dataPoint);
        return aggregationMessage;
    }
}
