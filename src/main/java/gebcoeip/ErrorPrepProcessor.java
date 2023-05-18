package gebcoeip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gebcoeip.json.EmailMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorPrepProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPrepProcessor.class);
    private static final String DEFAULT_ERROR = "An Unknown Error Occurred";

    private ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        LOGGER.error("Error caught:", cause);
        Object body = exchange.getIn().getBody();
        JsonNode json = null;
        if (body instanceof String) {
            try {
                json = objectMapper.readTree((String) body);
            } catch (Exception e) {
                json = null;
            }
        } else {
            try {
                json = objectMapper.readTree(objectMapper.writeValueAsString(body));
            } catch (Exception e) {
                json = null;
            }
        }

        String details = ExceptionUtils.getStackTrace(cause);
        if (details == null || details.isBlank()) {
            details = DEFAULT_ERROR;
        }

        String subject = "Unable to process file";
        if (json != null) {
            String fileId = null;
            if(json.hasNonNull("fileId")) {
                fileId = json.asText("fileId");
            }
            if(fileId != null && !fileId.isEmpty()) {
                subject = "Error Processing - " + fileId;
            }
        }

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setSubject(subject);
        emailMessage.setBody(details);
        exchange.getIn().setBody(objectMapper.writeValueAsString(emailMessage));

    }
}
