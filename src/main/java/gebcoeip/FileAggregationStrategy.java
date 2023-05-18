package gebcoeip;

import gebcoeip.json.AggregationMessage;
import gebcoeip.json.EmailMessage;

public class FileAggregationStrategy {


    public AggregationMessage aggregate(AggregationMessage oldMessage, AggregationMessage newMessage) {
        if (oldMessage == null) {
            return newMessage;
        }
        if (newMessage.getDataPoint() != null) {
            oldMessage.setPointSaveCount(oldMessage.getPointSaveCount() + 1);
        }
        if (newMessage.getMessage() != null) {
            oldMessage.setMessage(newMessage.getMessage());
        }
        oldMessage.setComplete(
                oldMessage.getMessage() != null &&
                        oldMessage.getMessage().getDataPoints().size() >= oldMessage.getPointSaveCount()
        );
        return oldMessage;
    }

    public EmailMessage email(AggregationMessage aggregationMessage) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setSubject("File Processing Complete - " + aggregationMessage.getFileId());
        emailMessage.setBody("File Processing Complete - " + aggregationMessage.getFileId());
        return emailMessage;
    }
}
